package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.Evaluation;

public interface I_EvaluationService {

	 Evaluation saveEvaluation(Evaluation evaluation);
	 Evaluation getEvaluationById(Long id);
	 List<Evaluation> getAllEvaluations();
	 void deleteEvaluationById(Long id);
}
