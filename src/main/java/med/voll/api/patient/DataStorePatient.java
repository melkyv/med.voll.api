package med.voll.api.patient;

import med.voll.api.adress.AdressData;

public record DataStorePatient(String name, String email, String phone, String cpf, AdressData adress) {
}
