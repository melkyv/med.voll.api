package med.voll.api.medic;

import jakarta.validation.constraints.NotNull;
import med.voll.api.adress.AdressData;

public record DataUpdateMedic(
        @NotNull
        Long id,
        String name,
        String phone,
        AdressData adress) {
}
