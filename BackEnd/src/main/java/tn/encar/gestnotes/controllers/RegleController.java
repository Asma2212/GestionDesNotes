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

import lombok.AllArgsConstructor;
import tn.encar.gestnotes.models.entities.Regle;
import tn.encar.gestnotes.services.impl.RegleService;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/regles")
public class RegleController {

	@Autowired
	private final RegleService regleService;
	
	 @GetMapping("/get/{id}")
	    public ResponseEntity<Regle> getRegleById(@PathVariable int id) {
	        Regle regle = regleService.getRegleById(id);
	        if (regle != null) {
	            return new ResponseEntity<>(regle, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @GetMapping("/All")
	    public ResponseEntity<List<Regle>> getAllRegles() {
	        List<Regle> regles = regleService.getAllRegles();
	        if (!regles.isEmpty()) {
	            return new ResponseEntity<>(regles, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	    }

	    @PostMapping("/save")
	    public ResponseEntity<Regle> saveRegle(@RequestBody Regle regle) {
	        Regle savedRegle = regleService.saveRegle(regle);
	        return new ResponseEntity<>(savedRegle, HttpStatus.CREATED);
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Void> deleteRegle(@PathVariable int id) {
	        regleService.deleteRegle(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    @PostMapping("/{idRegle}/departements/{idDepartement}")
	    public ResponseEntity<Void> assignRegelToDepartement(@PathVariable int idRegle, @PathVariable int idDepartement) {
	        regleService.assignRegelToDepartement(idRegle, idDepartement);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }

	    @DeleteMapping("/{idRegle}/departements/{idDepartement}")
	    public ResponseEntity<Void> removeRegleFromDepartement(@PathVariable int idRegle, @PathVariable int idDepartement) {
	        regleService.removeRegleFromDepartement(idRegle, idDepartement);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	
}
