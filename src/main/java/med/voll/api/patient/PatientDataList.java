package med.voll.api.patient;

public record PatientDataList(Long id, String name, String email, String cpf) {

    public PatientDataList(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
