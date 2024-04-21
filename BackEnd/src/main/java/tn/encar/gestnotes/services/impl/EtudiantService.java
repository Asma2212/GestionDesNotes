package tn.encar.gestnotes.services.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.config.JwtService;
import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Enseignant;
import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.enums.Role;
import tn.encar.gestnotes.models.enums.TypeFormation;
import tn.encar.gestnotes.repositories.EtudiantRepository;
import tn.encar.gestnotes.services.I_EtudiantService;
import tn.encar.gestnotes.services.dto.AuthResponse;
import tn.encar.gestnotes.models.entities.Note;

@Service
public class EtudiantService implements I_EtudiantService{

    @Autowired
    private EtudiantRepository etudiantRepository;
    
    @Autowired
    EmailSenderService emailSenderService;
	
    @Autowired
	private JwtService jwtService ;
    
    @Autowired
	private PasswordEncoder passwordEncoder;
    
	@Autowired
	private AuthenticationManager authManager;

    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant getEtudiantById(int id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return (List<Etudiant>) etudiantRepository.findAll();
    }
    
    @Override
    public List<Etudiant> getAllEtudiantsByClasse(Classe classe) {
        return (List<Etudiant>) etudiantRepository.findByClasse(classe);
    }

    @Override
    public void deleteEtudiantById(int id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public Etudiant getEtudiantByNumInscri(int numInscri) {
        return etudiantRepository.findByNumInscri(numInscri);
    }


    @Override
    public void deleteEtudiantByNumInscri(int numInscri) {
        etudiantRepository.deleteByNumInscri(numInscri);
    }


  /*  @Override
    public List<Etudiant> findByNiveauAndTypeFormation(String niveau, TypeFormation formation) {
        return etudiantRepository.findByNiveauAndTypeFormation(niveau, formation.toString());
    }*/
    
    @Override
    public List<Etudiant> findByTypeFormation(TypeFormation formation) {
        return etudiantRepository.findByTypeFormation(formation);
    }

    
    @Override
    public int countAllEtudiants() {
        return 1;
    }

	@Override
    public List<Etudiant> findByAgeGreaterThan(Date dateNaiss) {
        return etudiantRepository.findByDateNaissGreaterThan(dateNaiss);
    }
	@Override
	public Boolean existsByEmail(String email) {
		return etudiantRepository.existsByEmail(email);
	}

	@Override
	public Etudiant addNote(Note note) {
		
		return null;
	}
	
	public AuthResponse register(Etudiant request) {
		String mp = request.getMotDePasse() ;
		request.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
			saveEtudiant(request);
			emailSenderService.sendEmail(request.getEmail(), "testing", "Bienvenue à ENIC Notes", request.getCin(), mp,Role.ETUDIANT.name());
        var jwtToken = jwtService.generateToken(request);
		return AuthResponse.builder()
				.nom(request.getNom())
				.prenom(request.getPrenom())
				.token(jwtToken)
				.build();
	}
}