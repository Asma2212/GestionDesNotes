package tn.encar.gestnotes.controllers;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import lombok.AllArgsConstructor;
import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Departement;
import tn.encar.gestnotes.services.impl.ClasseService;


@AllArgsConstructor
@RestController
@RequestMapping("/api/classes")
public class ClasseController {

	@Autowired
	private final ClasseService classeService;
	
	 private static final Logger logger = LoggerFactory.getLogger(ClasseController.class);

	
	@GetMapping("/all")
    public ResponseEntity<List<Classe>> getClasses() {
        List<Classe> classes = classeService.getClasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }
	
	@GetMapping("/get/{id}")
    public ResponseEntity<Classe> getClassById(@PathVariable int id) {
        Classe classe = classeService.getClassseById(id);
        return classe != null ? ResponseEntity.ok(classe) : ResponseEntity.notFound().build();
    }
	
	@GetMapping("/niveau/{niveau}")
    public ResponseEntity<List<Classe>> getClassesByNiveau(@PathVariable int niveau) {
        List<Classe> classes = classeService.getClassesByNiveau(niveau);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }
	
	//find all classes belonging to a specific enseignant by it's id
	@GetMapping("/enseignant/{id}")
    public ResponseEntity<List<Classe>> getClassesByIdEnseignant(@PathVariable int id) {
		try {
        List<Classe> classes = classeService.getClassesByIdEnseignant(id);
        return new ResponseEntity<>(classes, HttpStatus.OK);
        }catch(IllegalStateException e) {
        	return ResponseEntity.notFound().build();
        }
    }
	
	@GetMapping("/{classeId}/departement")
	public  ResponseEntity<Departement> getDepartementByClasseId(@PathVariable int classeId) {
		Departement departement = classeService.getDepartementByClasseId(classeId);
		return ResponseEntity.ok(departement);
	}
	
	@PostMapping("/add")
    public ResponseEntity<List<Classe>> addNewClasse(@RequestBody Classe classe) {
        try {
            classeService.addNewClassse(classe);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

		
    }
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClasseById(@PathVariable int id) {
        try {
            classeService.deleteClasseById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	//count classes by level
	@GetMapping("/count/niveau/{niveau}")
    public ResponseEntity<Integer> countClasseByNiveau(@PathVariable int niveau) {
        int count = classeService.countClasseByNiveau(niveau);
        return ResponseEntity.ok(count);
    }
}
