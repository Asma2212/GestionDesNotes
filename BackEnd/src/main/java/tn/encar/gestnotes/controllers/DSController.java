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

import tn.encar.gestnotes.models.entities.DS;
import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.entities.Matiere;
import tn.encar.gestnotes.services.impl.DSService;
import tn.encar.gestnotes.services.impl.EtudiantService;
import tn.encar.gestnotes.services.impl.MatiereService;

@RestController
@RequestMapping("/api/ds")
public class DSController {

	@Autowired
	DSService dsService;
	
	@Autowired
	EtudiantService etudiantService;
	
	@Autowired
	MatiereService matiereService;
	
    @PostMapping("/save")
    public ResponseEntity<DS> saveDS(@RequestBody DS ds) {
    	int idEtd;
    	Long idMatiere;
    	Etudiant etudiant;
    	Matiere matiere;
    	if(ds.getEtudiant() != null) {
    	    idEtd = ds.getEtudiant().getId();	
    	    etudiant = etudiantService.getEtudiantById(idEtd);
        	ds.setEtudiant(etudiant);
    	}
    	if(ds.getMatiere() != null) {
    	idMatiere = ds.getMatiere().getIdMatiere();
    	matiere = matiereService.getMatiereById(idMatiere);
    	ds.setMatiere(matiere);
    	}
        DS savedDS = dsService.saveDS(ds);
        return new ResponseEntity<>(savedDS, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DS> getDSById(@PathVariable Long id) {
        DS DS = dsService.getDSById(id);
        if (DS != null) {
            return new ResponseEntity<>(DS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DS>> getAllDSs() {
        List<DS> DSs = dsService.getAllDS();
        return new ResponseEntity<>(DSs, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDSById(@PathVariable Long id) {
        dsService.deleteDSById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

