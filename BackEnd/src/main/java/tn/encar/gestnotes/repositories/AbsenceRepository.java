package tn.encar.gestnotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Absence;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long>{

}
