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

import tn.encar.gestnotes.models.entities.CC;
import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.entities.Matiere;
import tn.encar.gestnotes.services.impl.CCService;
import tn.encar.gestnotes.services.impl.EtudiantService;
import tn.encar.gestnotes.services.impl.MatiereService;

@RestController
@RequestMapping("/api/note/cc")
public class CCController {

	@Autowired
	CCService ccService;
	
	@Autowired
	EtudiantService etudiantService;
	
	@Autowired
	MatiereService matiereService;
	
    @PostMapping("/save")
    public ResponseEntity<CC> saveCC(@RequestBody CC cc) {
    	int idEtd;
    	Long idMatiere;
    	Etudiant etudiant;
    	Matiere matiere;
    	if(cc.getEtudiant() != null) {
    	    idEtd = cc.getEtudiant().getId();	
    	    etudiant = etudiantService.getEtudiantById(idEtd);
        	cc.setEtudiant(etudiant);
    	}
    	if(cc.getMatiere() != null) {
    	idMatiere = cc.getMatiere().getIdMatiere();
    	matiere = matiereService.getMatiereById(idMatiere);
    	cc.setMatiere(matiere);
    	}
        CC savedCC = ccService.saveCC(cc);
        return new ResponseEntity<>(savedCC, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CC> getCCById(@PathVariable Long id) {
        CC CC = ccService.getCCById(id);
        if (CC != null) {
            return new ResponseEntity<>(CC, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CC>> getAllCCs() {
        List<CC> CCs = ccService.getAllCC();
        return new ResponseEntity<>(CCs, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCCById(@PathVariable Long id) {
        ccService.deleteCCById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
