package med.voll.api.domain.consultation.validations;

import med.voll.api.domain.ValidExcpetion;
import med.voll.api.domain.consultation.DataSchedulingConsultation;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidatorTimeInAdvance implements ValidatorSchedulingConsultation{

    public void validate(DataSchedulingConsultation data) {
        var dateConsultation = data.date();
        var now = LocalDateTime.now();
        var differInMinutes = Duration.between(now, dateConsultation).toMinutes();

        if (differInMinutes < 30) {
            throw new ValidExcpetion("Consultation must be scheduled at least 30 minutes in advance!");
        }
    }
}
