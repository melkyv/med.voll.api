package med.voll.api.domain.medic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.adress.AdressData;

public record DataUpdateMedic(
        @NotNull
        Long id,
        String name,
        String phone,
        @Valid
        AdressData adress) {
}
