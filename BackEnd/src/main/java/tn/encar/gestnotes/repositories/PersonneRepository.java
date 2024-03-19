package tn.encar.gestnotes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Personne;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, Integer>{

	public List<Personne> findByEmail(String email);

	public List<Personne> findByCin(int cin);

}
