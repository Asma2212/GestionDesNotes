package tn.encar.gestnotes.services.impl;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tn.encar.gestnotes.config.JwtService;
import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Enseignant;
import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.entities.Personne;
import tn.encar.gestnotes.models.enums.Role;
import tn.encar.gestnotes.models.enums.Statut;
import tn.encar.gestnotes.repositories.ClasseRepository;
import tn.encar.gestnotes.repositories.EnseignantRepository;
import tn.encar.gestnotes.repositories.PersonneRepository;
import tn.encar.gestnotes.services.I_EnseignantService;
import tn.encar.gestnotes.services.dto.AuthResponse;
import tn.encar.gestnotes.services.dto.SignUpDTO;


@Service
public class EnseignantService implements I_EnseignantService{

	@Autowired
	private EnseignantRepository enseignantRepository;
	
	@Autowired
	private ClasseRepository classeRepository; 
	
	@Autowired
	private PersonneRepository personneRepository;
	
    @Autowired
    EmailSenderService emailSenderService;
	
    @Autowired
	private JwtService jwtService ;
    
    @Autowired
	private PasswordEncoder passwordEncoder;
    
	@Autowired
	private AuthenticationManager authManager;
	
	public Enseignant saveEnseignant(Enseignant enseignant) {
		return enseignantRepository.save(enseignant);
	}
	
	@Override
	public List<Enseignant> getEnseignants() {
		return (List<Enseignant>) enseignantRepository.findAll();
	}

	@Override
	public List<Enseignant> getEnseignantByPosition(Statut position) {
		return  enseignantRepository.findByPosition(position);
	}
	
	@Override
	public Enseignant getEnseignantById(int id) {
		return enseignantRepository.findById(id).get();
	}
	
	@Override
	public Boolean existsByEmail(String email) {
		return enseignantRepository.existsByEmail(email);
	}

	@Override
	public List<Enseignant> getEnseignantByClass(int idClass) {
		Classe classes = classeRepository.findClassById(idClass);
		return new ArrayList<>(classes.getEnseignants());
	}

	
	@Override
	public List<Enseignant> getEnseignantByLevelClass(int niveau) {
		List<Classe> classes = classeRepository.findClassesByNiveau(niveau);
	    Set<Enseignant> enseignants = new HashSet<>(); // Using a Set to avoid duplicates

	    for (Classe classe : classes) {
	        enseignants.addAll(classe.getEnseignants());
	    }

	    return new ArrayList<>(enseignants);
	}
	
	@Override
	public void addNewEnseignant(Enseignant enseignant) {
		if (!personneRepository.findByEmail(enseignant.getEmail()).isEmpty()
	            || !personneRepository.findByCin(enseignant.getCin()).isEmpty()) {
	        throw new IllegalStateException("Email or CIN already exists!");
	    }

	    // Check if the position is DIRECTEUR, DIRECTEUR_DES_ETUDES, or DIRECTEUR_DES_STAGES
	    if (enseignant.getPosition() == Statut.DIRECTEUR 
	            || enseignant.getPosition() == Statut.DIRECTEUR_DES_ETUDES
	            || enseignant.getPosition() == Statut.DIRECTEUR_DES_STAGES) {
	        // Check if an Enseignant with the same position already exists
	        if (!enseignantRepository.findByPosition(enseignant.getPosition()).isEmpty()) {
	            throw new IllegalStateException("An Enseignant with the position " + enseignant.getPosition() + " already exists!");
	        }
	    }

	    enseignantRepository.save(enseignant);
	}

	
	@Transactional
	@Override
	public void updatePositionById(int id, Statut position) {
		Enseignant enseignant = enseignantRepository.findById(id)
				.orElseThrow(()->new IllegalStateException("Enseignant avec id "+id+" n'existe pas"));
		if(position != null && !position.equals(enseignant.getPosition()))
			if (position != Statut.NONE) {
                // Check if an enseignant with the new position already exists
                if (!enseignantRepository.findByPosition(position).isEmpty()) {
                    throw new IllegalStateException("An Enseignant with the position " + position + " already exists!");
                }
            }

            // Update the position
            enseignant.setPosition(position);
            enseignantRepository.save(enseignant);
		
	}
	
	
	@Override
	public void deleteEnseignantById(int id) {
		if(!enseignantRepository.existsById(id))
			throw new IllegalStateException("Enseignant avec id "+id+" n'existe pas!");
		enseignantRepository.deleteById(id);
	}

	@Override
	public int countClassOfEnseignantById(int id) {
		Enseignant enseignant = enseignantRepository.findById(id).get();
		if(enseignant == null)
			throw new IllegalStateException();
	    if (enseignant != null && enseignant.getClassesAffectees() != null) {
	        return enseignant.getClassesAffectees().size();
	    }
	    return 0;
	}

	@Override
	public Enseignant assignClasseToEnseignant(int enseignantId, int classeId) {
		Enseignant enseignant = enseignantRepository.findById(enseignantId).get();
		Classe classe = classeRepository.findById(classeId).get();
		enseignant.getClassesAffectees().add(classe);
		return enseignantRepository.save(enseignant);
	}

	@Override
	public Enseignant removeClasseFromEnseignant(int enseignantId, int classeId) {
		Enseignant enseignant = enseignantRepository.findById(enseignantId).get();
		Classe classe = classeRepository.findById(classeId).get();
		enseignant.getClassesAffectees().remove(classe);
		return enseignantRepository.save(enseignant);
	}
	
	public AuthResponse register(Enseignant request) {
		String mp = request.getMotDePasse() ;
		request.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
			saveEnseignant(request);
			emailSenderService.sendEmail(request.getEmail(), "testing", "Bienvenue à ENIC Notes", request.getCin(), "#####" ,Role.ENSEIGNANT.name());
        var jwtToken = jwtService.generateToken(request);
		return AuthResponse.builder()
				.nom(request.getNom())
				.prenom(request.getPrenom())
				.token(jwtToken)
				.build();
	}
	
	public void registerEnsByAdmin(Enseignant request) {
		String mp = request.getMotDePasse();
		request.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
			saveEnseignant(request);
			emailSenderService.sendEmail(request.getEmail(), "testing", "Bienvenue à ENIC Notes", request.getCin(), mp,Role.ENSEIGNANT.name());
	}

}
