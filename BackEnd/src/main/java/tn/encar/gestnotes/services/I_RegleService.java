package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.Regle;

public interface I_RegleService {

	public Regle getRegleById(int id);
	public Regle saveRegle(Regle regle);
	public void deleteRegle(int id);
	public List<Regle> getAllRegles();
	public void assignRegelToDepartement(int idRegle, int idDepartement);
	public void removeRegleFromDepartement(int idRegle, int idDepartement);
	
}
