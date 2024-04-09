package tn.encar.gestnotes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Regle;


@Repository
public interface RegleRepository extends CrudRepository<Regle, Integer>{
	
}
