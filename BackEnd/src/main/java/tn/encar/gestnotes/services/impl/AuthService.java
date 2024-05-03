package tn.encar.gestnotes.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.encar.gestnotes.config.JwtService;
import tn.encar.gestnotes.models.entities.Enseignant;
import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.entities.Personne;
import tn.encar.gestnotes.models.enums.Role;
import tn.encar.gestnotes.repositories.PersonneRepository;
import tn.encar.gestnotes.services.dto.AuthResponse;
import tn.encar.gestnotes.services.dto.SignInDTO;
import tn.encar.gestnotes.services.dto.SignUpDTO;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	@Autowired
	private final PersonneRepository personneRepo;
	
	@Autowired
	private EtudiantService etudiantService;
	
	@Autowired
	private EnseignantService enseignantService;
	
    @Autowired
    EmailSenderService emailSenderService;
    
	private static boolean isAuth=false ;
	private final JwtService jwtService ;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authManager;

	public AuthResponse register(SignUpDTO request,Role r) {
		String mp = request.getMotDePasse() ;
		request.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
		Personne user = null;
		switch (r) {
		case ETUDIANT: {
			user = new Etudiant();
			user.setEmail(request.getEmail());
			user.setMotDePasse(request.getMotDePasse());
			emailSenderService.sendEmail(user.getEmail(), "testing", "Bienvenue à ENIC Notes", user.getCin(), mp,user.getRole().name());
			//etudiantService.saveEtudiant((Etudiant)user);
		};break;
		case ENSEIGNANT:{
			user = new Enseignant();
			user.setEmail(request.getEmail());
			user.setMotDePasse(request.getMotDePasse());
			enseignantService.saveEnseignant((Enseignant)user);
		}
		default:
			;
		}
        var jwtToken = jwtService.generateToken(user);
		return AuthResponse.builder()
				.token(jwtToken)
				.build();
	}
	
	public AuthResponse login(SignInDTO request) {
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getMotDePasse()));
		var user = personneRepo.findByEmail(request.getEmail())
				.orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        isAuth = true;
      return  AuthResponse.builder()
        		.nom(user.getNom())
        		.prenom(user.getPrenom())
				.token(jwtToken)
				.build();
				}
	
	public AuthResponse registerEtd(Etudiant request) {
		String mp = request.getMotDePasse() ;
		request.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
			//saveEnseignant(request);
			emailSenderService.sendEmail(request.getEmail(), "testing", "Bienvenue à ENIC Notes", request.getCin(), mp,request.getRole().name());
        var jwtToken = jwtService.generateToken(request);
		return AuthResponse.builder()
				.token(jwtToken)
				.build();
	}
	
	public boolean isUserAuthenticated() {
        return isAuth;
    }

}
