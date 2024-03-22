package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.Classe;

public interface I_ClasseService {

	
	public List<Classe> getClasses();
	public Classe getClassseById(int id);
	public List<Classe> getClassesByNiveau(int niveau);
	public List<Classe> getClassesByIdEnseignant(int id);
	public List<Classe> getClassesByIdDepartement(int id);
	public void addNewClassse(Classe classe);
	public void deleteClasseById(int id);
	public int countClasseByNiveau(int niveau);
	
}
