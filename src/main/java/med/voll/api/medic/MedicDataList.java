package med.voll.api.medic;

public record MedicDataList(String name, String email, String crm, Specialty specialty) {

    public MedicDataList(Medic medic) {
        this(medic.getName(), medic.getEmail(), medic.getCrm(), medic.getSpecialty());
    }
}
