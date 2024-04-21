package tn.encar.gestnotes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import tn.encar.gestnotes.models.entities.Admin;
import tn.encar.gestnotes.models.entities.Enseignant;
import tn.encar.gestnotes.models.entities.Personne;
import tn.encar.gestnotes.models.enums.AdminRole;
import tn.encar.gestnotes.models.enums.Role;
import tn.encar.gestnotes.repositories.AdminRepository;
import tn.encar.gestnotes.services.dto.SignInDTO;
import tn.encar.gestnotes.services.impl.AdminService;
import tn.encar.gestnotes.services.impl.AuthService;
import tn.encar.gestnotes.services.impl.EmailSenderService;
import tn.encar.gestnotes.services.impl.PersonneService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admins")
public class AdminController {

	@Autowired
	private final AdminService adminService;
	
	@Autowired
	private final PersonneService personneService;
	
    @Autowired
    private AuthService authService;
	
    @Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private final PersonneService personneSerivce; 
	
    @Autowired
    EmailSenderService emailSenderService;
    
	
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Admin signUpAd) {
        if (!personneService.findByEmail(signUpAd.getEmail()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email deja existant!");
        }      
        signUpAd.setRole(Role.ETUDIANT);
        return ResponseEntity.ok(adminService.register(signUpAd));
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
    public String envoyerCode(@RequestBody Admin ad) {
        System.out.println("******" + ad);
        if (adminService.existsByEmail(ad.getUsername())) {

        }
        String code = "" ;

int r = (int)Math.floor(Math.random() * (99999999 - 10000000) + 10000000) ;
code = Integer.toString(r);
System.out.println("******" + r);
        emailSenderService.sendEmailConfirm(ad.getEmail(), "testing", "récupération de compte",ad.getNom()+" "+ad.getPrenom(), code);
        return code;
    }
    
	 @GetMapping("/get-email/{email}")
	    public ResponseEntity<Admin> getAdminByEmail(@PathVariable String email) {
	        Admin admin = adminService.getAdminByEmail(email) ;
	        if (admin != null) {
	            return new ResponseEntity<>(admin, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 @GetMapping("/get/{id}")
	    public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
	        Admin admin = adminService.getAdminById(id);
	        if (admin != null) {
	            return new ResponseEntity<>(admin, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @GetMapping("/all")
	    public ResponseEntity<List<Admin>> getAllAdmins() {
	        List<Admin> admins = adminService.getAllAdmins();
	        if (!admins.isEmpty()) {
	            return new ResponseEntity<>(admins, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	    }

	    @PostMapping("/save")
	    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin) {
	    	try {
	    		adminService.saveAdmin(admin);
	    		return new ResponseEntity<>(HttpStatus.CREATED);
	    	}catch(IllegalStateException e) {
	    		return new ResponseEntity<>(HttpStatus.CONFLICT);
	    	}
	        
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
	        try {
	        	adminService.deleteAdmin(id);
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }catch(IllegalStateException e) {
	        	return ResponseEntity.notFound().build();
	        }
	    	
	    }

	    @GetMapping("/get/by-role/{roleAdmin}")
	    public ResponseEntity<List<Admin>> getAdminsByRole(@PathVariable AdminRole roleAdmin) {
	        List<Admin> admins = adminService.getAdminsByRole(roleAdmin);
	        if (!admins.isEmpty()) {
	            return ResponseEntity.ok(admins);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	    }

	    @PatchMapping("/update-roleAdmin/{id}")
	    public ResponseEntity<Void> updateRoleAdminById(@PathVariable int id, @RequestParam AdminRole roleAdmin) {
	        try {
	        	adminService.updateRoleAdminById(id, roleAdmin);
		        return new ResponseEntity<>(HttpStatus.OK);
	        }catch(IllegalStateException e) {
	        	return ResponseEntity.notFound().build();
	        }
	    	
	    }
	    
	    @PatchMapping("/update-email/{id}")
	    public ResponseEntity<Void> updateEmailAdminById(@PathVariable int id,@RequestParam String email){
	    	try {
	    		personneSerivce.updateEmailById(email, id);
	    		return new ResponseEntity<>(HttpStatus.OK);
	    	}catch(IllegalStateException e) {
	    		return ResponseEntity.badRequest().build();
	    	}
	    	
	    }
	    
	    @PatchMapping("/update-motdepasse/{id}")
	    public ResponseEntity<Void> updateMotDePasseById(@PathVariable int id,@RequestParam String motdepasse){
	    	try {
	    		personneSerivce.updatePasswordById(motdepasse,id);
	    		return new ResponseEntity<>(HttpStatus.OK);
	    	}catch(IllegalStateException e) {
	    		return ResponseEntity.badRequest().build();
	    	}
	    	
	    }
	    @PostMapping ("/updatePassword/{anc}/{nouv}")
	    public  ResponseEntity<?> updatePassword(@Valid @RequestBody Admin ad,@PathVariable String anc,@PathVariable String nouv){
	    	String ancien = passwordEncoder.encode(anc);
	    	String nouveau = passwordEncoder.encode(nouv);
	    	Admin ad1 = adminService.getAdminById(ad.getId());

	        if(anc.equals(nouv)) {
	    		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("nouvelle mot de passe doit etre differente de l'ancienne ");
	    }else
	        if(passwordEncoder.matches(anc, ad1.getMotDePasse()))
	        {
	        	String passwordy =ad.getMotDePasse();
	        	passwordy = passwordEncoder.encode(passwordy);
	            ad.setMotDePasse(passwordy);
	        	
	          adminService.saveAdmin(ad);
	        	return ResponseEntity.ok().body("mot de passe modifiée");
	        }else
	        
	        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ancien mot de passe invalide");
	    }
	    @PostMapping ("/updatePass")
	    public  Admin updatePassAdmin(@Valid @RequestBody Admin ad){
	    	String passwordy =ad.getPassword();
	    	passwordy = passwordEncoder.encode(passwordy);
	        ad.setMotDePasse(passwordy);
	             return   adminService.saveAdmin(ad);
	    }
	    
}
