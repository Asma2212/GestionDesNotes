package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Departement;
import tn.encar.gestnotes.models.entities.Regle;

public interface I_DepartementService {
	
	public List<Departement> getDepartements();
	public Departement getDepartementById(int id);
	public List<Departement> getDepartementByNom(String nom);
	public List<Classe> getClassesByIdDepartement(int id);
	public Regle getRegleByDepartementId(int id);
	public Departement addNewDepartement(Departement departement);
	public void updateDepartement(int id, String nom);
	public void deleteDepartementById(int id);
	public int countDepartement();
	public int countClassesOfDepartementById(int id);
	public Departement assignClasseToDepartement(int departementId, int classeId);
	public Departement removeClasseFromDepartement(int departementId, int classeId);
}
