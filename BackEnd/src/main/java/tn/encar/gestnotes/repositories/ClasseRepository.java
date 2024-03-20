package tn.encar.gestnotes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Classe;


@Repository
public interface ClasseRepository extends CrudRepository<Classe, Integer>{

	public Classe findClassById(int id);
	
	public List<Classe> findClassesByNiveau(int niveau);
	
	public List<Classe> findClassesByGroupe(char groupe);
	
	public List<Classe> findClassesByNiveauAndGroupe(int niveau, char groupe);
	
	public int countByNiveau(int niveau);
	
}
