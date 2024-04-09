package tn.encar.gestnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.models.entities.Admin;
import tn.encar.gestnotes.models.enums.AdminRole;
import tn.encar.gestnotes.repositories.AdminRepository;
import tn.encar.gestnotes.repositories.PersonneRepository;
import tn.encar.gestnotes.services.I_AdminService;

@Service
public class AdminService implements I_AdminService {

	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PersonneRepository personneRepository;
	
	@Override
	public Admin getAdminById(int id) {
		return adminRepository.findByid(id);
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

}
