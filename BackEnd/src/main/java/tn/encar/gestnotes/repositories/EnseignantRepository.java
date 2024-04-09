package tn.encar.gestnotes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Enseignant;
import tn.encar.gestnotes.models.enums.Statut;


@Repository
public interface EnseignantRepository extends CrudRepository<Enseignant, Integer>{
	
	public Enseignant findByid(int id);
	
	public List<Enseignant> findByPosition(Statut position);
	
}
