package tn.encar.gestnotes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Departement;
import tn.encar.gestnotes.models.entities.Regle;


@Repository
public interface DepartementRepository extends CrudRepository<Departement, Integer>{

	public Departement findByid(int id);
	
	public List<Departement> findByNom(String nom);	 
	
	public Regle findRegleById(int id);
}
