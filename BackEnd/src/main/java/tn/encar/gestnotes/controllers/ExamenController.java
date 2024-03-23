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

import tn.encar.gestnotes.models.entities.Examen;
import tn.encar.gestnotes.services.impl.ExamenService;

@RestController
@RequestMapping("/api/examen")
public class ExamenController {

	@Autowired
	ExamenService examenService;
	
    @PostMapping("/save")
    public ResponseEntity<Examen> saveExamen(@RequestBody Examen examen) {
        Examen savedExamen = examenService.saveExamen(examen);
        return new ResponseEntity<>(savedExamen, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Examen> getExamenById(@PathVariable Long id) {
        Examen Examen = examenService.getExamenById(id);
        if (Examen != null) {
            return new ResponseEntity<>(Examen, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Examen>> getAllExamens() {
        List<Examen> Examens = examenService.getAllExamen();
        return new ResponseEntity<>(Examens, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExamenById(@PathVariable Long id) {
        examenService.deleteExamenById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

