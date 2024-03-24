package med.voll.api.domain.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medic.Specialty;

import java.time.LocalDateTime;

public record DataSchedulingConsultation(
        Long idMedic,
        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime date,
        Specialty specialty ) {
}
