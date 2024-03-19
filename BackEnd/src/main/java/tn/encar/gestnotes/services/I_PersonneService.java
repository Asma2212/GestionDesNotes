package tn.encar.gestnotes.services;

import java.sql.Date;
import java.util.List;

import tn.encar.gestnotes.models.entities.Personne;

public interface I_PersonneService {

	public List<Personne> getPersonnes();
	public Personne getPersonneById(int id);
    public List<Personne> getPersonnesByRole(String role);
    public Personne getPersonneByTel(int tel);
	public void addNewPersonne(Personne personne);
	public void deletePersonne(int  id);
	public void updatePersonne(int id, int cin, String nom, String prenom, int tel, String email, Date dateNaiss, String mdp);
	public void updateEmailById(String email, int employeId);
	public void updatePasswordById(String mpd, int id);
	public String getPersonnePrenomEtNomById(int employeId);
	
}
