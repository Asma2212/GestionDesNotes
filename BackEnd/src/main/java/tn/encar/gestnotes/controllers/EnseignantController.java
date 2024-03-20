package tn.encar.gestnotes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tn.encar.gestnotes.models.entities.Enseignant;
import tn.encar.gestnotes.models.enums.Statut;
import tn.encar.gestnotes.services.impl.EnseignantService;


@AllArgsConstructor
@RestController
@RequestMapping(path="/api/enseignants")
public class EnseignantController {

	@Autowired
	private final EnseignantService enseignantService;
	
	
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
	 
	 @GetMapping("/get/position/{position}")
	 public ResponseEntity<Enseignant> getEnseignantByPosition(@PathVariable Statut position){
		 Enseignant enseignant = enseignantService.getEnseignantByPosition(position);
		 if(enseignant != null )
			 return new ResponseEntity<>(enseignant, HttpStatus.OK);
		 else 
			 return new ResponseEntity<>(enseignant, HttpStatus.NOT_FOUND);
	 }

	 @GetMapping("/all")
	 public ResponseEntity<List<Enseignant>> getEnseignants() {
		 List<Enseignant> enseignant = enseignantService.getEnseignants();
	     return new ResponseEntity<>(enseignant, HttpStatus.OK);
	 }
	 
	 @GetMapping("/by-class/{idClass}")
	    public ResponseEntity<List<Enseignant>> getEnseignantByClass(@PathVariable int idClass) {
	        List<Enseignant> enseignants = enseignantService.getEnseignantByClass(idClass);
	        return ResponseEntity.ok(enseignants);
	    }

	    @GetMapping("/by-level-class/{niveau}")
	    public ResponseEntity<List<Enseignant>> getEnseignantByLevelClass(@PathVariable int niveau) {
	        List<Enseignant> enseignants = enseignantService.getEnseignantByLevelClass(niveau);
	        return ResponseEntity.ok(enseignants);
	    }

	 @DeleteMapping("/delete/{id}")
	 public ResponseEntity<Void> deleteEnseignantById(@PathVariable int id) {
		try {
		 enseignantService.deleteEnseignantById(id);
	     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(IllegalStateException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	 }
	 
	 // Controller method to update enseignant position by ID
	 @PutMapping("/get/{id}/position/position{}")
	 public ResponseEntity<Void> updatePositionById(
		 @PathVariable int id, 
	     @RequestParam Statut position){
	     try {
	    	 enseignantService.updatePositionById(id, position);
	         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	     }catch (IllegalThreadStateException e) {
	         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
	 }	 
	 
	 // Controller method to add a new enseignant
	    @PostMapping("/add")
	    public ResponseEntity<Void> addNewEnseignant(@RequestBody Enseignant enseignant) {
	        try {
	            enseignantService.addNewEnseignant(enseignant);
	            return new ResponseEntity<>(HttpStatus.CREATED);
	        } catch (IllegalStateException e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	    }
	    
	    
	    @PutMapping("/{enseignantId}/classe/{classeId}")
	    public Enseignant assignClasseToEnseignant(
	    		@PathVariable int enseignantId,
	    		@PathVariable int classeId
	    		) {	
	    	return enseignantService.assignClasseToEnseignant(enseignantId, classeId); 
	    }
	    
	    @GetMapping("/{id}/count-classes")
	    public ResponseEntity<Integer> countClassesOfEnseignantById(@PathVariable int id) {
	        int count = enseignantService.countClassOfEnseignantById(id);
	        return ResponseEntity.ok(count);
	    }
	    

	
}
