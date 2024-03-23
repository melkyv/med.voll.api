package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid DataStoreMedic data, UriComponentsBuilder uriBuilder) {
        var medic = new Medic(data);
        repository.save(medic);

        var uri = uriBuilder.path("/medics/{id}").buildAndExpand(medic.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailsMedic(medic));
    }

    @GetMapping
    public ResponseEntity<Page<MedicDataList>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page = repository.findAllByActiveTrue(pagination).map(MedicDataList::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateMedic data) {
        var medic = repository.getReferenceById(data.id());
        medic.updateData(data);

        return ResponseEntity.ok(new DataDetailsMedic(medic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var medic = repository.getReferenceById(id);
        medic.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity details(@PathVariable Long id) {
        var medic = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailsMedic(medic));
    }
}
