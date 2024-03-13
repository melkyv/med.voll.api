package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patient.DataStorePatient;
import med.voll.api.patient.Patient;
import med.voll.api.patient.PatientDataList;
import med.voll.api.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public Page<PatientDataList> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return repository.findAll(pagination).map(PatientDataList::new);
    }
}
