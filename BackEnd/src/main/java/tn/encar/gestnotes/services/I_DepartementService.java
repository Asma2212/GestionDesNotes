package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.Departement;

public interface I_DepartementService {
	
	public List<Departement> getDepartements();
	public Departement getDepartementById(int id);
	public List<Departement> getDepartementByNom(String nom);
	public void addNewDepartement(Departement departement);
	public void updateDepartement(int id, String nom);
	public void deleteDepartementById(int id);
	public int countDepartement();
	public int countClassesOfDepartementById(int id);
	public Departement assignClasseToDepartement(int departementId, int classeId);
}
