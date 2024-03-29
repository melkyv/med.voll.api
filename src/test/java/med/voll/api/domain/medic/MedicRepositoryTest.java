package med.voll.api.domain.medic;

import med.voll.api.domain.adress.AdressData;
import med.voll.api.domain.consultation.Consultation;
import med.voll.api.domain.patient.DataStorePatient;
import med.voll.api.domain.patient.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicRepositoryTest {

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("It should return null when the only registered doctor is not available on the date")
    void chooseRandomMedicAvailableInDateScene1() {
        //given or arrange
        var nextMondayAtTen = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medic = saveMedic("Medic", "test@voll.med", "123456", Specialty.CARDIOLOGY);
        var patient = savePatient("Patient", "patient@voll.med", "00000000000");
        saveConsultation(medic, patient, nextMondayAtTen);

        //when or act
        var medicFree = medicRepository.chooseRandomMedicAvailableInDate(Specialty.CARDIOLOGY, nextMondayAtTen);

        //then or assert
        assertThat(medicFree).isNull();
    }

    @Test
    @DisplayName("It should return medic when he is available on the date")
    void chooseRandomMedicAvailableInDateScene2() {
        //given or arrange
        var nextMondayAtTen = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medic = saveMedic("Medic", "test@voll.med", "123456", Specialty.CARDIOLOGY);

        //when or act
        var medicFree = medicRepository.chooseRandomMedicAvailableInDate(Specialty.CARDIOLOGY, nextMondayAtTen);

        //then or assert
        assertThat(medicFree).isEqualTo(medic);
    }

    private void saveConsultation(Medic medic, Patient patient, LocalDateTime date) {
        em.persist(new Consultation(null, medic, patient, date, null));
    }

    private Medic saveMedic(String name, String email, String crm, Specialty specialty) {
        var medic = new Medic(dataMedic(name, email, crm, specialty));
        em.persist(medic);
        return medic;
    }

    private Patient savePatient(String name, String email, String cpf) {
        var patient = new Patient(dataPatient(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DataStoreMedic dataMedic(String name, String email, String crm, Specialty specialty) {
        return new DataStoreMedic(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                dataAdress()
        );
    }

    private DataStorePatient dataPatient(String name, String email, String cpf) {
        return new DataStorePatient(
                name,
                email,
                "61999999999",
                cpf,
                dataAdress()
        );
    }

    private AdressData dataAdress() {
        return new AdressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}