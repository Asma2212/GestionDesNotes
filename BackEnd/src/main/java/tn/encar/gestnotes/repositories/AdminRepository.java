package tn.encar.gestnotes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.encar.gestnotes.models.entities.Admin;
import tn.encar.gestnotes.models.enums.AdminRole;


@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer>{

	public List<Admin> findByRoleAdmin(AdminRole roleAdmin);

	public Boolean existsByEmail(String email);

	public Admin findByEmail(String email);
	
}
