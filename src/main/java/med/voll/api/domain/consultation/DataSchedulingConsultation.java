package med.voll.api.domain.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataSchedulingConsultation(
        @NotNull
        Long idMedic,
        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime date) {
}
