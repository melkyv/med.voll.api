package med.voll.api.domain.consultation.validations;

import med.voll.api.domain.ValidExcpetion;
import med.voll.api.domain.consultation.ConsultationRepository;
import med.voll.api.domain.consultation.DataSchedulingConsultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorMedicInOtherConsultation implements ValidatorSchedulingConsultation{

    @Autowired
    private ConsultationRepository repository;

    public void validate(DataSchedulingConsultation data) {
        var medicInOtherConsultationInSameHour = repository.existsByMedicIdAndDate(data.idMedic(), data.date());

        if (medicInOtherConsultationInSameHour) {
            throw new ValidExcpetion("Medic has another consultation at the same time!");
        }
    }
}
