package tn.encar.gestnotes.services;

import java.sql.Date;
import java.util.List;

import tn.encar.gestnotes.entities.Personne;

public interface I_PersonneService {

	public List<Personne> getPersonnes();
	
	public void addNewPersonne(Personne personne);
	
	public void deletePersonne(int  id);
	
	public void updatePersonne(int id, int cin, String nom, String prenom, int tel, String email, Date dateNaiss, String mdp);
}
