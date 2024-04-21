package tn.encar.gestnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.config.JwtService;
import tn.encar.gestnotes.models.entities.Admin;
import tn.encar.gestnotes.models.enums.AdminRole;
import tn.encar.gestnotes.models.enums.Role;
import tn.encar.gestnotes.repositories.AdminRepository;
import tn.encar.gestnotes.repositories.PersonneRepository;
import tn.encar.gestnotes.services.I_AdminService;
import tn.encar.gestnotes.services.dto.AuthResponse;

@Service
public class AdminService implements I_AdminService {

	
	@Autowired
	private AdminRepository adminRepository;
	
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
	
	@Override
	public Admin getAdminById(int id) {
		return adminRepository.findById(id).get();
	}

	@Override
	public List<Admin> getAllAdmins() {
		return (List<Admin>) adminRepository.findAll();
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		if(!personneRepository.findByCin(admin.getCin()).isEmpty()
				|| !personneRepository.findByEmail(admin.getEmail()).isEmpty())
			throw new IllegalStateException("email ou cin existe deja!");
		return adminRepository.save(admin);
	}

	@Override
	public void deleteAdmin(int id) {
		if(!adminRepository.existsById(id))
			throw new IllegalStateException("Admin avec id "+id+"n'existe pas!");
		adminRepository.deleteById(id);
	}

	@Override
	public List<Admin> getAdminsByRole(AdminRole roleAdmin) {
		return adminRepository.findByRoleAdmin(roleAdmin);
	}

	@Override
	public void updateRoleAdminById(int id, AdminRole roleAdmin) {
		Admin admin = adminRepository.findById(id)
				.orElseThrow(()->new IllegalStateException("Admin avec id "+id+" n'existe pas!"));
		if(admin != null)
			admin.setRoleAdmin(roleAdmin);
		adminRepository.save(admin);
	}
	
	public AuthResponse register(Admin request) {
		String mp = request.getMotDePasse() ;
		request.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
			saveAdmin(request);
			emailSenderService.sendEmail(request.getEmail(), "testing", "Bienvenue à ENIC Notes", request.getCin(), mp,Role.ADMIN.name());
        var jwtToken = jwtService.generateToken(request);
		return AuthResponse.builder()
				.nom(request.getNom())
				.prenom(request.getPrenom())
				.token(jwtToken)
				.build();
	}
	
	@Override
	public Boolean existsByEmail(String email) {
		return adminRepository.existsByEmail(email);
	}
	
	@Override
	public Admin getAdminByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

}
