package tn.encar.gestnotes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.encar.gestnotes.models.entities.Absence;
import tn.encar.gestnotes.services.impl.AbsenceService;

@RestController
@RequestMapping("/api/absence")
public class AbsenceController {

	@Autowired
	AbsenceService absenceService;
	
    @PostMapping("/save")
    public ResponseEntity<Absence> saveAbsence(@RequestBody Absence absence) {
        Absence savedAbsence = absenceService.saveAbsence(absence);
        return new ResponseEntity<>(savedAbsence, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Absence> getAbsenceById(@PathVariable Long id) {
        Absence absence = absenceService.getAbsenceById(id);
        if (absence != null) {
            return new ResponseEntity<>(absence, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Absence>> getAllAbsences() {
        List<Absence> absences = absenceService.getAllAbsence();
        return new ResponseEntity<>(absences, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAbsenceById(@PathVariable Long id) {
        absenceService.deleteAbsenceById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
