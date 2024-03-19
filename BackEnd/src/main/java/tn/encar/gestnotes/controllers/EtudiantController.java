package tn.encar.gestnotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.entities.Personne;
import tn.encar.gestnotes.repositories.PersonneRepository;
import tn.encar.gestnotes.services.dto.SignInEtudiantDTO;
import tn.encar.gestnotes.services.dto.SignUpEtudiantDTO;
import tn.encar.gestnotes.services.impl.EtudiantService;
import tn.encar.gestnotes.services.impl.PersonneService;


import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;
    
    @Autowired
    private PersonneRepository personneService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpEtudiantDTO signUpEtd) {
        if (personneService.findByEmail(signUpEtd.getEmail()).size()!=0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("email is already taken!");
        }

        // Create new user
        Etudiant etd = new Etudiant();
        etd.setEmail(signUpEtd.getEmail());
        etd.setMotDePasse(passwordEncoder.encode(signUpEtd.getMotDePasse())); // Encrypt password
        
        etudiantService.saveEtudiant(etd);
        
        return ResponseEntity.ok("User registered successfully");
    }


    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInEtudiantDTO signInEtd) {
        Personne etd = personneService.findByEmail(signInEtd.getEmail()).get(0);
        
        if (etd != null && passwordEncoder.matches(signInEtd.getEmail(), etd.getMotDePasse())) {
            return ResponseEntity.ok("User authenticated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }
    
    @PostMapping("/save")
    public ResponseEntity<Etudiant> saveEtudiant(@RequestBody Etudiant etudiant) {
        Etudiant savedEtudiant = etudiantService.saveEtudiant(etudiant);
        return new ResponseEntity<>(savedEtudiant, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable int id) {
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        if (etudiant != null) {
            return new ResponseEntity<>(etudiant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

}
