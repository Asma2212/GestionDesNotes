package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.Enseignant;
import tn.encar.gestnotes.models.enums.Statut;

public interface I_EnseignantService {

	public List<Enseignant> getEnseignants();
	public Enseignant getEnseignantById(int id);
	public Enseignant getEnseignantByPosition(Statut position);
	public List<Enseignant> getEnseignantByClass(int idClass);
	public List<Enseignant> getEnseignantByLevelClass(int niveau);
	public void addNewEnseignant(Enseignant enseignant);
	public void updatePositionById(int id, Statut position);
	public void deleteEnseignantById(int id);
	public int countClassOfEnseignantById(int id);
		
}
