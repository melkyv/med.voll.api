package med.voll.api.domain.consultation;

import med.voll.api.domain.ValidExcpetion;
import med.voll.api.domain.medic.Medic;
import med.voll.api.domain.medic.MedicRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void schedule(DataSchedulingConsultation data) {
        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidExcpetion("Patient id not exists!");
        }

        if (data.idMedic() != null && !medicRepository.existsById(data.idMedic())) {
            throw new ValidExcpetion("Medic id not exists!");
        }

        var medic = chooseMedic(data);
        var patient = patientRepository.getReferenceById(data.idPatient());
        var consultation = new Consultation(null, medic, patient, data.date(), null);

        consultationRepository.save(consultation);
    }

    private Medic chooseMedic(DataSchedulingConsultation data) {
        if (data.idMedic() != null) {
            return medicRepository.getReferenceById(data.idMedic());
        }

        if (data.specialty() == null) {
            throw new ValidExcpetion("Specialty is required when a medic is not chosen!");
        }

        return medicRepository.chooseRandomMedicAvailableInDate(data.specialty(), data.date());
    }

    public void cancel(DataCancelConsultation data) {
        if (!consultationRepository.existsById(data.idConsultation())) {
            throw new ValidExcpetion("Consultation not exists!");
        }

        var consultation = consultationRepository.getReferenceById(data.idConsultation());
        consultation.cancelConsultation(data.reason());
    }
}
