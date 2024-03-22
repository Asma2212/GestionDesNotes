package tn.encar.gestnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Departement;
import tn.encar.gestnotes.repositories.ClasseRepository;
import tn.encar.gestnotes.repositories.DepartementRepository;
import tn.encar.gestnotes.services.I_DepartementService;


@Service
public class DepartementService implements I_DepartementService{

	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	private ClasseRepository classeRepository;
	
	
	
	@Override
	public List<Departement> getDepartements() {		
		return (List<Departement>) departementRepository.findAll();
	}

	@Override
	public Departement getDepartementById(int id) {
		return departementRepository.findDepartementById(id);
	}

	@Override
	public List<Departement> getDepartementByNom(String nom) {
		return departementRepository.findByNom(nom);
	}


	@Override
	public void addNewDepartement(Departement departement) {
		if(!departementRepository.findByNom(departement.getNom()).isEmpty())
			throw new IllegalStateException("departement par le nom "+departement.getNom()+" existe deja!");
		departementRepository.save(departement);
		
	}

	@Override
	public void updateDepartement(int id, String nom) {
		Departement department = departementRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Department avec id " + id+" n'existe pas!"));
        
        department.setNom(nom);
        departementRepository.save(department);
		
	}

	@Override
	public void deleteDepartementById(int id) {
		Departement department = departementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department avec id " + id+" n'existe pas!"));
        
        departementRepository.delete(department);
		
	}

	@Override
	public int countDepartement() {
		return (int) departementRepository.count();
	}

	@Override
	public int countClassesOfDepartementById(int id) {
		Departement department = departementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department avec id " + id+" n'existe pas!"));
        
        return department.getClasses().size();
	}

	@Override
	public Departement assignClasseToDepartement(int departementId, int classeId) {
		Departement departement = departementRepository.findDepartementById(departementId);
        Classe classe = classeRepository.findClassById(classeId);
        departement.getClasses().add(classe);
        return departementRepository.save(departement);
	}

	@Override
	public Departement removeClasseFromDepartement(int departementId, int classeId) {
		Departement departement = departementRepository.findDepartementById(departementId);
        Classe classe = classeRepository.findClassById(classeId);
        departement.getClasses().remove(classe);
		return departementRepository.save(departement);
	}

}
