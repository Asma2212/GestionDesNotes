package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.Admin;
import tn.encar.gestnotes.models.enums.AdminRole;

public interface I_AdminService {
	
	 public Admin getAdminById(int id);
	 public List<Admin> getAllAdmins();
	 public Admin saveAdmin(Admin admin);
	 public void deleteAdmin(int id);
	 public List<Admin> getAdminsByRole(AdminRole roleAdmin);
	 public void updateRoleAdminById(int id, AdminRole roleAdmin);
	Boolean existsByEmail(String email);
	Admin getAdminByEmail(String email);
}
