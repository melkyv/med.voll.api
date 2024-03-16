package med.voll.api.domain.patient;

import med.voll.api.domain.adress.Adress;

public record DataDetailsPatient(Long id, String name, String cpf, String email, String phone, Adress adress) {
    public DataDetailsPatient(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getCpf(), patient.getEmail(), patient.getPhone(), patient.getAdress());
    }
}
