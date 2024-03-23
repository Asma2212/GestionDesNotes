package tn.encar.gestnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Evaluation;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long>{

}
