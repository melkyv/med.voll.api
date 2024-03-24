package med.voll.api.domain.consultation.validations;

import med.voll.api.domain.ValidExcpetion;
import med.voll.api.domain.consultation.DataSchedulingConsultation;
import med.voll.api.domain.medic.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorMedicActive implements ValidatorSchedulingConsultation{

    @Autowired
    private MedicRepository repository;

    public void validate(DataSchedulingConsultation data) {
        if (data.idMedic() == null) {
            return;
        }

        var medicActive = repository.findActiveById(data.idMedic());
        if (!medicActive) {
            throw new ValidExcpetion("Consultation cannot be scheduled with an inactive medic!");
        }
    }
}
