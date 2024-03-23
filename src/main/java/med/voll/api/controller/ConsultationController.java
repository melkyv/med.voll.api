package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consultation.DataDetailsConsultation;
import med.voll.api.domain.consultation.DataSchedulingConsultation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultations")
public class ConsultationController {

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid DataSchedulingConsultation data) {

        return ResponseEntity.ok(new DataDetailsConsultation(null, null, null, null));
    }
}
