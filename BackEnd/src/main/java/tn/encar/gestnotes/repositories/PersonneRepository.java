package tn.encar.gestnotes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Personne;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, Integer>{

	public Optional<Personne> findByEmail(String email);

	public List<Personne> findByCin(int cin);
	
	public Personne findByid(int id);
	
	public List<Personne> findByRole(String role);
	
	public Personne findByTel(int tel);

}
