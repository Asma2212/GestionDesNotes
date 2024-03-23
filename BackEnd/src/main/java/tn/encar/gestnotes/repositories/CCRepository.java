package tn.encar.gestnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.CC;

@Repository
public interface CCRepository extends JpaRepository<CC, Long>{

}
