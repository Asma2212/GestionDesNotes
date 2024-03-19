package tn.encar.gestnotes.services.impl;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import tn.encar.gestnotes.models.entities.Personne;
import tn.encar.gestnotes.repositories.PersonneRepository;
import tn.encar.gestnotes.services.I_PersonneService;

@Service
@AllArgsConstructor
public class PersonneService implements I_PersonneService {

	@Autowired
	private final PersonneRepository personneRepository; 
	
	@Override
	public List<Personne> getPersonnes() {
		return (List<Personne>) personneRepository.findAll();
	}	
	
	@Override
	public Personne getPersonneById(int id) {
		return personneRepository.findById1(id);
	}

	@Override
	public List<Personne> getPersonnesByRole(String role) {
		return personneRepository.findByRole(role);
	}

	
	@Override
	public Personne getPersonneByTel(int tel) {
		return personneRepository.findByTel(tel);
	}
	
	@Override
	public void addNewPersonne(Personne personne) {
		
		if(!personneRepository.findByEmail(personne.getEmail()).isEmpty())
			throw new IllegalStateException("personne par email "+personne.getEmail()+"existe deja!");
		
		personneRepository.save(personne);
	}

	@Override
	public void deletePersonne(int id) {
		if(!personneRepository.existsById(id))
			throw new IllegalStateException("Personne avec id "+id+"n'existe pas.");
		personneRepository.deleteById(id);
	}

	@Override
	public void updatePersonne(int id, int cin, String nom, String prenom, int tel, String email, Date dateNaiss, String mdp) {
		
		Personne personne = personneRepository.findById(id).orElseThrow(()->new IllegalStateException("Personne par id "+id+" n'existe pas!"));

		if(cin != 0 && cin != personne.getCin()) {
			if(personneRepository.findByCin(cin) != null)
				throw new IllegalStateException("cin existe deja!");
		}
		
		if (nom != null && !nom.isEmpty() && !Objects.equals(nom, personne.getNom())) {
		  personne.setNom(nom);
		}
		if (prenom != null && !prenom.isEmpty() && !Objects.equals(prenom, personne.getPrenom())) {
		  personne.setPrenom(prenom);
		}
		if (tel != 0) {
		  personne.setTel(tel);
		}
		if (email != null && !email.isEmpty() && !Objects.equals(email, personne.getEmail())) {
		  if(!personneRepository.findByEmail(email).isEmpty())
			  throw new IllegalStateException("email existe deja!");
			
		  personne.setEmail(email);
		}
		if (dateNaiss != null && dateNaiss != personne.getDateNaiss()) {
		  personne.setDateNaiss(dateNaiss);
		}
		if (mdp != null && !mdp.isEmpty() && mdp != personne.getMotDePasse()) {
		  personne.setMotDePasse(mdp);
		}
	
		personneRepository.save(personne);
		
	}


	@Override
	public void updateEmailById(String email, int employeId) {
		Personne personne = personneRepository.findById1(employeId);
		if(!personneRepository.findByEmail(email).isEmpty() && !Objects.equals(email, personne.getEmail()))
			personne.setEmail(email);
		
		personneRepository.save(personne);
	}

	@Override
	public void updatePasswordById(String mpd, int id) {
		Personne personne = personneRepository.findById1(id);
		if(!Objects.equals(mpd, personne.getMotDePasse()))
			personne.setMotDePasse(mpd);
		personneRepository.save(personne);
	}
	
	@Override
	public String getPersonnePrenomEtNomById(int employeId) {
	    Personne personne = personneRepository.findById1(employeId);
	    if (personne != null) 
	        return personne.getPrenom() + " " + personne.getNom();
	    else 
	        return null;
	}
	

}
