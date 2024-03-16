package med.voll.api.domain.medic;

import med.voll.api.domain.adress.Adress;

public record DataDetailsMedic(Long id, String name, String phone, String email, Specialty specialty, Adress adress) {

    public DataDetailsMedic(Medic medic) {
        this(medic.getId(), medic.getName(), medic.getPhone(), medic.getEmail(), medic.getSpecialty(), medic.getAdress());
    }
}
