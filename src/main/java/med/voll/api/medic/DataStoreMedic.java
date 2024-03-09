package med.voll.api.medic;

import med.voll.api.adress.AdressData;

public record DataStoreMedic(String name, String email, String phone, String crm, Specialty specialty, AdressData adress) {
}
