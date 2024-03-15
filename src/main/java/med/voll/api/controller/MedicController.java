package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public void store(@RequestBody @Valid DataStoreMedic data) {
        repository.save(new Medic(data));
    }

    @GetMapping
    public Page<MedicDataList> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(MedicDataList::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DataUpdateMedic data) {
        var medic = repository.getReferenceById(data.id());
        medic.updateData(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var medic = repository.getReferenceById(id);
        medic.delete();
    }
}
