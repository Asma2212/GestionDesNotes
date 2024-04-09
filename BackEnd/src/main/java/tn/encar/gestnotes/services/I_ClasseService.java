package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Departement;

public interface I_ClasseService {

	
	public List<Classe> getClasses();
	public Classe getClassseById(int id);
	public List<Classe> getClassesByNiveau(int niveau);
	public List<Classe> getClassesByIdEnseignant(int id);
	public Departement getDepartementByClasseId(int classeId);
	public void addNewClassse(Classe classe);
	public void deleteClasseById(int id);
	public int countClasseByNiveau(int niveau);
	
}
