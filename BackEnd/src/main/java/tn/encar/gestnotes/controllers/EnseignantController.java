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

import tn.encar.gestnotes.models.entities.Enseignant;
import tn.encar.gestnotes.services.impl.EnseignantService;

@RestController
@RequestMapping(path="/api/enseignants")
public class EnseignantController {

	@Autowired
	private EnseignantService enseignantService;
	
	
	 @PostMapping("/save")
	 public ResponseEntity<Enseignant> saveEnseignant(@RequestBody Enseignant enseignant) {
		 Enseignant savedEnseignant = enseignantService.saveEnseignant(enseignant);
	     return new ResponseEntity<>(savedEnseignant, HttpStatus.CREATED);
	 }

	 @GetMapping("/get/{id}")
	 public ResponseEntity<Enseignant> getEnseignantById(@PathVariable int id) {
	   	Enseignant enseignant = enseignantService.getEnseignantById(id);
        if (enseignant != null) {
        	return new ResponseEntity<>(enseignant, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	 }

	 @GetMapping("/all")
	 public ResponseEntity<List<Enseignant>> getEnseignants() {
		 List<Enseignant> enseignant = enseignantService.getEnseignants();
	     return new ResponseEntity<>(enseignant, HttpStatus.OK);
	 }

	 @DeleteMapping("/delete/{id}")
	 public ResponseEntity<Void> deleteEnseignantById(@PathVariable int id) {
		 enseignantService.deleteEnseignantById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	 }

	
}
