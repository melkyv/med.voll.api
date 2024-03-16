package med.voll.api.domain.medic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.adress.Adress;

@Table(name = "medics")
@Entity(name = "Medic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Adress adress;

    public Medic(DataStoreMedic data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.crm = data.crm();
        this.phone = data.phone();
        this.specialty = data.specialty();
        this.adress = new Adress(data.adress());
    }

    public void updateData(DataUpdateMedic data) {
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
