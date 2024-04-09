package tn.encar.gestnotes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tn.encar.gestnotes.models.entities.Admin;
import tn.encar.gestnotes.models.enums.AdminRole;
import tn.encar.gestnotes.services.impl.AdminService;
import tn.encar.gestnotes.services.impl.PersonneService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admins")
public class AdminController {

	@Autowired
	private final AdminService adminService;
	
	
	@Autowired
	private final PersonneService personneSerivce; 
	
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
}
