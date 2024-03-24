package med.voll.api.domain.consultation.validations;

import med.voll.api.domain.ValidExcpetion;
import med.voll.api.domain.consultation.ConsultationRepository;
import med.voll.api.domain.consultation.DataSchedulingConsultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorPatientWithConsultationInSameDay implements ValidatorSchedulingConsultation{

    @Autowired
    private ConsultationRepository repository;

    public void validate(DataSchedulingConsultation data) {
        var firstTime = data.date().withHour(7);
        var lastTime = data.date().withHour(18);
        var patientHasOtherConsultationInDay = repository.existsByPatientIdAndDateBetween(data.idPatient(), firstTime, lastTime);

        if (patientHasOtherConsultationInDay) {
            throw new ValidExcpetion("Patient has a consultation in same day!");
        }
    }
}
