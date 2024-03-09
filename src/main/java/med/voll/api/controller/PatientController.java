package med.voll.api.controller;

import med.voll.api.patient.DataStorePatient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @PostMapping
    public void store(@RequestBody DataStorePatient data) {
        System.out.println(data);
    }
}
