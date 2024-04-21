package tn.encar.gestnotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import tn.encar.gestnotes.config.JwtService;
import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.entities.Personne;
import tn.encar.gestnotes.models.enums.Role;
import tn.encar.gestnotes.repositories.PersonneRepository;
import tn.encar.gestnotes.services.dto.AuthResponse;
import tn.encar.gestnotes.services.dto.SignInDTO;
import tn.encar.gestnotes.services.dto.SignUpDTO;
import tn.encar.gestnotes.services.impl.AuthService;
import tn.encar.gestnotes.services.impl.ClasseService;
import tn.encar.gestnotes.services.impl.EmailSenderService;
import tn.encar.gestnotes.services.impl.EtudiantService;
import tn.encar.gestnotes.services.impl.PersonneService;

import java.util.List;

@RestController
@RequestMapping("/api/etudiant")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;
    
    @Autowired
    private PersonneService personneService;
    
    @Autowired
    private ClasseService classeService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    EmailSenderService emailSenderService;
    
    @Autowired
	private JwtService jwtService ;
    

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Etudiant signUpEtd) {
        if (!personneService.findByEmail(signUpEtd.getEmail()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email deja existant!");
        }    
    	int idClasse;
    	if(signUpEtd.getClasse()!= null) {
    		idClasse = signUpEtd.getClasse().getId();
    		signUpEtd.setClasse(classeService.getClassseById(idClasse));
    	}
        signUpEtd.setRole(Role.ETUDIANT);
        return ResponseEntity.ok(etudiantService.register(signUpEtd));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInDTO signInEtd) {
        Personne etd = personneService.findByEmail(signInEtd.getEmail()).get();
        if (etd != null && passwordEncoder.matches(signInEtd.getMotDePasse(), etd.getMotDePasse())) {
        	return ResponseEntity.ok(authService.login(signInEtd));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou mot de passe invalide");
        }
    }
    
    @PostMapping(path = "/code") //, consumes = MediaType.APPLICATION_JSON_VALUE
    public String envoyerCode(@RequestBody Etudiant etd) {
        System.out.println("******" + etd);
        if (etudiantService.existsByEmail(etd.getUsername())) {

        }
        String code = "" ;

int r = (int)Math.floor(Math.random() * (99999999 - 10000000) + 10000000) ;
code = Integer.toString(r);
System.out.println("******" + r);
        emailSenderService.sendEmailConfirm(etd.getEmail(), "testing", "récupération de compte",etd.getNom()+" "+etd.getPrenom(), code);
        return code;
    }
    
    @PostMapping("/save")
    public ResponseEntity<Etudiant> saveEtudiant(@RequestBody Etudiant etudiant) {
        Etudiant savedEtudiant = etudiantService.saveEtudiant(etudiant);
        return new ResponseEntity<>(savedEtudiant, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEtudiantById(@PathVariable int id) {
    	System.out.println("getEtudiant");
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        System.out.println(": "+etudiant);
        if (etudiant != null) {
            return new ResponseEntity<>(etudiant, HttpStatus.OK);
        } else {
        	 return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body("Etudiant introuvable");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEtudiantById(@PathVariable int id) {
        etudiantService.deleteEtudiantById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/allByClasse/{idClasse}")
    public ResponseEntity<List<Etudiant>> getAllEtudiantsByClasse(@PathVariable int idClasse) {
    	Classe classe = classeService.getClassseById(idClasse);
        List<Etudiant> etudiants = etudiantService.getAllEtudiantsByClasse(classe);
        return ResponseEntity.ok(etudiants);
    }

}
