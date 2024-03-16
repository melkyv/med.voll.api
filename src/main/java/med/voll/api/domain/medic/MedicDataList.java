package med.voll.api.domain.medic;

public record MedicDataList(Long id, String name, String email, String crm, Specialty specialty) {

    public MedicDataList(Medic medic) {
        this(medic.getId(), medic.getName(), medic.getEmail(), medic.getCrm(), medic.getSpecialty());
    }
}
