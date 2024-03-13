package med.voll.api.patient;

public record PatientDataList(String name, String email, String cpf) {

    public PatientDataList(Patient patient){
        this(patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
