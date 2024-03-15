package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patient.*;
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
        return repository.findAllByActiveTrue(pagination).map(PatientDataList::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DataUpdatePatient data) {
        var patient = repository.getReferenceById(data.id());
        patient.update(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();
    }
}
