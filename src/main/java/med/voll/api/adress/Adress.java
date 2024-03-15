package med.voll.api.adress;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Adress {

    private String place;
    private String neighborhood;
    private String zip;
    private String city;
    private String uf;
    private String complement;
    private String number;

    public Adress(AdressData adress) {
        this.place = adress.place();
        this.neighborhood = adress.neighborhood();
        this.zip = adress.zip();
        this.city = adress.city();
        this.uf = adress.uf();
        this.complement = adress.complement();
        this.number = adress.number();
    }

    public void updateData(AdressData adress) {
        if (adress.place() != null) {
            this.place = adress.place();
        }

        if (adress.neighborhood() != null) {
            this.neighborhood = adress.neighborhood();
        }

        if (adress.city() != null) {
            this.city = adress.city();
        }

        if (adress.zip() != null) {
            this.zip = adress.zip();
        }

        if (adress.uf() != null) {
            this.uf = adress.uf();
        }

        if (adress.complement() != null) {
            this.complement = adress.complement();
        }

        if (adress.number() != null) {
            this.number = adress.number();
        }
    }
}
