package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patient.DataStorePatient;
import med.voll.api.patient.Patient;
import med.voll.api.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientRepository repository;

    @PostMapping
    @Transactional
    public void store(@RequestBody @Valid DataStorePatient data) {
        repository.save(new Patient(data));
    }
}
