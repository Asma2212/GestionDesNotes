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

import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.entities.Evaluation;
import tn.encar.gestnotes.models.entities.Matiere;
import tn.encar.gestnotes.services.impl.EtudiantService;
import tn.encar.gestnotes.services.impl.EvaluationService;
import tn.encar.gestnotes.services.impl.MatiereService;

@RestController
@RequestMapping("/api/note/evaluation")
public class EvaluationController {

	@Autowired
	EvaluationService evaluationService;
	
	@Autowired
	EtudiantService etudiantService;
	
	@Autowired
	MatiereService matiereService;
	
    @PostMapping("/save")
    public ResponseEntity<Evaluation> saveEvaluation(@RequestBody Evaluation evaluation) {
    	evaluation.setType("Evaluation");
    	int idEtd;
    	Long idMatiere;
    	Etudiant etudiant;
    	Matiere matiere;
    	if(evaluation.getEtudiant() != null) {
    	    idEtd = evaluation.getEtudiant().getId();	
    	    etudiant = etudiantService.getEtudiantById(idEtd);
    	    evaluation.setEtudiant(etudiant);
    	}
    	if(evaluation.getMatiere() != null) {
    	idMatiere = evaluation.getMatiere().getIdMatiere();
    	matiere = matiereService.getMatiereById(idMatiere);
    	evaluation.setMatiere(matiere);
    	}
        Evaluation savedEvaluation = evaluationService.saveEvaluation(evaluation);
        return new ResponseEntity<>(savedEvaluation, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable Long id) {
        Evaluation Evaluation = evaluationService.getEvaluationById(id);
        if (Evaluation != null) {
            return new ResponseEntity<>(Evaluation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Evaluation>> getAllEvaluations() {
        List<Evaluation> Evaluations = evaluationService.getAllEvaluations();
        return new ResponseEntity<>(Evaluations, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvaluationById(@PathVariable Long id) {
        evaluationService.deleteEvaluationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

