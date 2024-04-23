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

import tn.encar.gestnotes.models.entities.Matiere;
import tn.encar.gestnotes.services.impl.MatiereService;

@RestController
@RequestMapping("/api/matiere")
public class MatiereController {

	@Autowired
	MatiereService matiereService;
	
    @PostMapping("/save")
    public ResponseEntity<?> saveMatiere(@RequestBody Matiere matiere) {
    	if(matiereService.findByNomMatiere(matiere.getNomMatiere()) == null) {
    	Matiere savedMatiere = matiereService.saveMatiere(matiere);
        return new ResponseEntity<>(savedMatiere, HttpStatus.CREATED);	
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.CONFLICT).body("Cette matiere existe déjà");
    	}
    }
    
    @PostMapping("/update")
    public ResponseEntity<?> updateMatiere(@RequestBody Matiere matiere) {
    	Matiere savedMatiere = matiereService.saveMatiere(matiere);
        return new ResponseEntity<>(savedMatiere, HttpStatus.CREATED);	
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMatiereById(@PathVariable Long id) {
        Matiere matiere = matiereService.getMatiereById(id);
        if (matiere != null) {
            return new ResponseEntity<>(matiere, HttpStatus.OK);
        } else {
        	return ResponseEntity.status(HttpStatus.CONFLICT).body("Cette matiere n'existe pas");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Matiere>> getAllMatieres() {
        List<Matiere> Matieres = matiereService.getAllMatiere();
        return new ResponseEntity<>(Matieres, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMatiereById(@PathVariable Long id) {
        matiereService.deleteMatiereById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

