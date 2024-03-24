package med.voll.api.domain.consultation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medic.Medic;
import med.voll.api.domain.patient.Patient;

import java.time.LocalDateTime;

@Entity(name = "Consultation")
@Table(name = "consultations")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consultation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medic_id")
    private Medic medic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "date_consultation")
    private LocalDateTime date;

    @Column(name = "cancellation_reason")
    @Enumerated(EnumType.STRING)
    private CancellationReason cancellationReason;

    public void cancelConsultation(CancellationReason reason) {
        this.cancellationReason = reason;
    }
}
