package tn.encar.gestnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Matiere;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long>{

}
