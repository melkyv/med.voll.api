package med.voll.api.domain.consultation.validations;

import med.voll.api.domain.ValidExcpetion;
import med.voll.api.domain.consultation.DataSchedulingConsultation;
import med.voll.api.domain.medic.MedicRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorPatientActive implements ValidatorSchedulingConsultation{

    @Autowired
    private PatientRepository repository;

    public void validate(DataSchedulingConsultation data) {
        var patientActive = repository.findActiveById(data.idPatient());

        if (!patientActive) {
            throw new ValidExcpetion("Consultation cannot be scheduled with an inactive patient!");
        }
    }
}
