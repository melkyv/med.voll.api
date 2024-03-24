package med.voll.api.domain.consultation;

import java.time.LocalDateTime;

public record DataDetailsConsultation(Long id, Long idMedic, Long idPatient, LocalDateTime date) {
    public DataDetailsConsultation(Consultation consultation) {
        this(consultation.getId(), consultation.getMedic().getId(), consultation.getPatient().getId(), consultation.getDate());
    }
}
