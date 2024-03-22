package tn.encar.gestnotes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Personne;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, Integer>{

	public List<Personne> findByEmail(String email);

	public Personne findByCin(int cin);
	
	public Personne findByid(int id);
	
	public List<Personne> findByRole(String role);
	
	public Personne findByTel(int tel);

}
