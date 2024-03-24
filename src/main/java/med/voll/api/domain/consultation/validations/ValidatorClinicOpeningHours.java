package med.voll.api.domain.consultation.validations;

import med.voll.api.domain.ValidExcpetion;
import med.voll.api.domain.consultation.DataSchedulingConsultation;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidatorClinicOpeningHours implements ValidatorSchedulingConsultation{

    public void validate(DataSchedulingConsultation data) {
        var dateConsultation = data.date();

        var sunday = dateConsultation.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeClinicalOpening = dateConsultation.getHour() < 7;
        var afterClinicClosure = dateConsultation.getHour() > 18;

        if (sunday || beforeClinicalOpening || afterClinicClosure) {
            throw new ValidExcpetion("Consultation outside clinic opening hours!");
        }
    }
}
