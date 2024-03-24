package med.voll.api.domain.consultation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Boolean existsByMedicIdAndDate(Long idMedic, LocalDateTime date);

    Boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstTime, LocalDateTime lastTime);
}
