package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.adress.Adress;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    private Boolean active;

    @Embedded
    private Adress adress;

    public Patient(DataStorePatient patient) {
        this.active = true;
        this.name = patient.name();
        this.email = patient.email();
        this.phone = patient.phone();
        this.cpf = patient.cpf();
        this.adress = new Adress(patient.adress());
    }

    public void update(DataUpdatePatient data) {
        if (data.name() != null) {
            this.name = data.name();
        }

        if (data.phone() != null) {
            this.phone = data.phone();
        }

        if (data.adress() != null) {
            this.adress.updateData(data.adress());
        }
    }

    public void delete() {
        this.active = false;
    }
}
