package tn.encar.gestnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.models.entities.Evaluation;
import tn.encar.gestnotes.repositories.EvaluationRepository;
import tn.encar.gestnotes.services.I_EvaluationService;

@Service
public class EvaluationService implements I_EvaluationService{
	
	@Autowired
	private EvaluationRepository evaluationRepository ;

	@Override
	public Evaluation saveEvaluation(Evaluation evaluation) {
		return evaluationRepository.save(evaluation);
	}

	@Override
	public Evaluation getEvaluationById(Long id) {
		return evaluationRepository.findById(id).orElse(null);
	}

	@Override
	public List<Evaluation> getAllEvaluations() {
		return evaluationRepository.findAll();
	}

	@Override
	public void deleteEvaluationById(Long id) {
		evaluationRepository.deleteById(id);
		
	}
	

}
