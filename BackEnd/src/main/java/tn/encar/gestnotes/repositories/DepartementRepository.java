package tn.encar.gestnotes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Departement;


@Repository
public interface DepartementRepository extends CrudRepository<Departement, Integer>{

	public Departement findDepartementById(int id);
	
	public List<Departement> findByNom(String nom);
	
	public Departement findByClassesId(int classeId);

}
