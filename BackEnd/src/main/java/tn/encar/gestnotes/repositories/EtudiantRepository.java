package tn.encar.gestnotes.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.enums.TypeFormation;

@Repository
public interface EtudiantRepository extends CrudRepository<Etudiant, Integer>{
	
	
	Boolean existsByEmail(String email);
	
    Etudiant findByNumInscri(int numInscri);

    List<Etudiant> findByNiveau(int niveau);
    
    List<Etudiant> findByTypeFormation(TypeFormation formation);

  //  List<Etudiant> findByNiveauAndTypeFormation(String niveau, String formation);

    void deleteByNumInscri(int numInscri);

    int countByNiveau(int niveau);
    
    Etudiant findById(int id);
    
    void deleteById(int id);
    
   /* @Query("SELECT COUNT(e) FROM Etudiant e")
    int countAllEtudiants()*/
    
    @Query("SELECT e FROM Personne e WHERE e.dateNaiss > :dateNaiss and ROLE = 'ETUDIANT'")
    List<Etudiant> findByDateNaissGreaterThan(@Param("dateNaiss") Date dateNaiss);
    
}
