package tn.encar.gestnotes.services;

import java.sql.Date;
import java.util.List;

import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.entities.Note;
import tn.encar.gestnotes.models.enums.TypeFormation;

public interface I_EtudiantService {
	
	public List<Etudiant> getAllEtudiants();
	public void deleteEtudiantById(int id);
	public Etudiant getEtudiantById(int id);
	public Etudiant getEtudiantByNumInscri(int numInscri);
	public void deleteEtudiantByNumInscri(int numInscri);
	//public List<Etudiant> findByNiveauAndTypeFormation(String niveau, TypeFormation formation);
	public List<Etudiant> findByTypeFormation(TypeFormation formation);
	public List<Etudiant> findByAgeGreaterThan(Date dateNaiss);
	public int countAllEtudiants();
	Boolean existsByEmail(String email);
	public Etudiant addNote(Note note);
	List<Etudiant> getAllEtudiantsByClasse(Classe classe);
	Etudiant getEtudiantByEmail(String email);
}
