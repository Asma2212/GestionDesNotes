package tn.encar.gestnotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import tn.encar.gestnotes.config.JwtService;
import tn.encar.gestnotes.models.entities.Admin;
import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.entities.Matiere;
import tn.encar.gestnotes.models.entities.Note;
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
	 @GetMapping("/get-email/{email}")
	    public ResponseEntity<Etudiant> getAdminByEmail(@PathVariable String email) {
	        Etudiant etd = etudiantService.getEtudiantByEmail(email) ;
	        if (etd != null) {
	            return new ResponseEntity<>(etd, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 
	 @GetMapping("/allNotes/{id}")
	    public ResponseEntity< Set<Note>> getNotes(@PathVariable int id) {
	        Set<Note> note = etudiantService.getEtudiantById(id).getNotes() ;
	        if (note != null) {
	            return new ResponseEntity<>(note, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 
	 @GetMapping("/allMatiere/{email}")
	 public ResponseEntity<Map<String, Set<Note>>> getMatieres(@PathVariable String email) {
		    Set<Note> notes = etudiantService.getEtudiantByEmail(email).getNotes();
		    Map<String, Set<Note>> matiereNotes = new HashMap<>();

		    for (Note note : notes) {
		        String matiere = "Matiere : "+note.getMatiere().getNomMatiere()+" - Coeff : "+note.getMatiere().getCoeff();
		        matiereNotes.computeIfAbsent(matiere, k -> new HashSet<>()).add(note);
		    }

		    if (!matiereNotes.isEmpty()) {
		        return new ResponseEntity<>(matiereNotes, HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		}
	 @GetMapping("/moyParMatiere/{email}")
	 public ResponseEntity<Map<String, Double>> getMoyParMatiere(@PathVariable String email) {
	     Set<Note> notes = etudiantService.getEtudiantByEmail(email).getNotes();
	     Map<String, Set<Note>> matiereNotes = new HashMap<>();
	     for (Note note : notes) {
	    	 String matiere = "Matiere : "+note.getMatiere().getNomMatiere()+" - Coeff : "+note.getMatiere().getCoeff();
	         matiereNotes.computeIfAbsent(matiere, k -> new HashSet<>()).add(note);
	     }

	     Map<String, Double> matiereMoy = new HashMap<>();
	     for (Map.Entry<String, Set<Note>> entry : matiereNotes.entrySet()) {
	         String matiere = entry.getKey();
	         Set<Note> notesForMatiere = entry.getValue();
	         double sum = 0.0;
	         /*for (Note note : notesForMatiere) {
	        	 if(note.getType().equals("DS")) {
	        		 sum += note.getNote()*0.27;
	        		 System.out.println("notee"+sum);
	        	 }
	        		 
	        	 else
	        		 if(note.getType().equals("Evaluation"))
	        		   sum += note.getNote()*0.07;
	        		 else
	        			 if(note.getType().equals("Examen"))
	        				 sum += note.getNote()*0.65;
	         }*/
        	 for (Note note : notesForMatiere) {
                 sum += note.getNote();
             }
	         double moyenne = sum / notesForMatiere.size();
	         matiereMoy.put(matiere, moyenne);
	     }

	     if (!matiereMoy.isEmpty()) {
	         return new ResponseEntity<>(matiereMoy, HttpStatus.OK);
	     } else {
	         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	     }
	 }
	 @GetMapping("/calculMoyGenerale/{email}")
	 public ResponseEntity<Double> calculMoyGenerale(@PathVariable String email) {
	     Set<Note> notes = etudiantService.getEtudiantByEmail(email).getNotes();
	     double moy = 0.0;
	     int totCoef = 0;
	     Map<Matiere, Set<Note>> matiereNotes = new HashMap<>();
	     for (Note note : notes) {
	         matiereNotes.computeIfAbsent(note.getMatiere(), k -> new HashSet<>()).add(note);
	     }

	     for (Map.Entry<Matiere, Set<Note>> entry : matiereNotes.entrySet()) {
	         double sum = 0.0;
	         Set<Note> notesForMatiere = entry.getValue();
	        	 for (Note note : notesForMatiere) {
	                 sum += note.getNote();
	             }
	         double moyenne = sum / notesForMatiere.size();
	         moy += moyenne * entry.getKey().getCoeff();
	         totCoef += entry.getKey().getCoeff();
	     }
	     
	     if (totCoef != 0) {
	         moy = moy / totCoef;
	         return new ResponseEntity<>(moy, HttpStatus.OK);
	     } else {
	         // Retourner zéro ou une valeur par défaut si totCoef est égal à zéro
	         return new ResponseEntity<>(0.0, HttpStatus.OK);
	     }
	 }
	 
}
