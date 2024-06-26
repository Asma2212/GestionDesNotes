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
import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Departement;
import tn.encar.gestnotes.models.entities.Regle;
import tn.encar.gestnotes.services.impl.DepartementService;


@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/departements")
public class DepartementController {

	@Autowired
	private final DepartementService departementService;
	
	@GetMapping("/all")
    public ResponseEntity<List<Departement>> getDepartements() {
        List<Departement> departements = departementService.getDepartements();
        return ResponseEntity.ok(departements);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Departement> getDepartementById(@PathVariable int id) {
    		Departement departement = departementService.getDepartementById(id);
            return ResponseEntity.ok(departement);
    	
        
    }

    @GetMapping("/get/nom/{nom}")
    public ResponseEntity<List<Departement>> getDepartementByNom(@PathVariable String nom) {
        List<Departement> departements = departementService.getDepartementByNom(nom);
        return ResponseEntity.ok(departements);
    }
    
    @GetMapping("/get-classes/{id}")
    public ResponseEntity<List<Classe>> getClassesByIdDepartement(@PathVariable int id){
    	List<Classe> classes = departementService.getClassesByIdDepartement(id);
    	return ResponseEntity.ok(classes);
    }

    @GetMapping("/get-regle/{id}")
    public ResponseEntity<Regle> getRegleByDepartementId(@PathVariable int id) {
    	Regle regle = departementService.getRegleByDepartementId(id);
    	return ResponseEntity.ok(regle);
    }
    
    @PostMapping("/add")
    public ResponseEntity<Departement> addNewDepartement(@RequestBody Departement departement) {
        departementService.addNewDepartement(departement);
        return new ResponseEntity<>(departementService.addNewDepartement(departement),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateDepartement(@PathVariable int id, @RequestParam String nom) {
        departementService.updateDepartement(id, nom);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDepartementById(@PathVariable int id) {
        departementService.deleteDepartementById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countDepartement() {
        int count = departementService.countDepartement();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{id}/count-classes")
    public ResponseEntity<Integer> countClassesOfDepartementById(@PathVariable int id) {
        int count = departementService.countClassesOfDepartementById(id);
        return ResponseEntity.ok(count);
    }

    @PostMapping("/{departementId}/classe/{classeId}")
    public ResponseEntity<Departement> assignClasseToDepartement(@PathVariable int departementId,
                                                                 @PathVariable int classeId) {
    		Departement departement = departementService.assignClasseToDepartement(departementId, classeId);
            return ResponseEntity.ok(departement);
        
    }
    
    
    @DeleteMapping("/{departementId}/classe/{classeId}")
    public ResponseEntity<Departement> removeClasseFromDepartement(@PathVariable int departementId,
                                                                 @PathVariable int classeId) {
        Departement departement = departementService.removeClasseFromDepartement(departementId, classeId);
        return ResponseEntity.ok(departement);
    }
    
}
