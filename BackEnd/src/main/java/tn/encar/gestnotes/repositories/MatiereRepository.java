package tn.encar.gestnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Matiere;
import tn.encar.gestnotes.models.entities.Personne;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long>{
	public Matiere findByNomMatiere(String nomMatiere);
}
