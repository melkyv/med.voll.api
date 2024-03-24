package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consultation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    private ConsultationRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid DataSchedulingConsultation data) {
        var dto = consultationService.schedule(data);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid DataCancelConsultation data) {
        consultationService.cancel(data);

        return ResponseEntity.noContent().build();
    }
}
