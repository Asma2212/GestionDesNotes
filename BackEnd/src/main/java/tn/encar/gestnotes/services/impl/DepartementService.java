package tn.encar.gestnotes.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Departement;
import tn.encar.gestnotes.models.entities.Regle;
import tn.encar.gestnotes.repositories.ClasseRepository;
import tn.encar.gestnotes.repositories.DepartementRepository;
import tn.encar.gestnotes.repositories.RegleRepository;
import tn.encar.gestnotes.services.I_DepartementService;


@Service
public class DepartementService implements I_DepartementService{

	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	private ClasseRepository classeRepository;

	@Autowired
	private RegleRepository regleRepository;
	
	@Override
	public List<Departement> getDepartements() {		
		return (List<Departement>) departementRepository.findAll();
	}

	@Override
	public Departement getDepartementById(int id) {
		Departement departement = departementRepository.findById(id).orElseThrow(()->new IllegalStateException("Departement avec id "+id+" n'existe pas!"));
		return departement;
	}

	@Override
	public List<Departement> getDepartementByNom(String nom) {
		return departementRepository.findByNom(nom);
	}

	@Override
	public List<Classe> getClassesByIdDepartement(int id) {
		Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department avec id " + id+" n'existe pas!"));
		return new ArrayList<>(departement.getClasses());
	}
	
	@Override
	public Regle getRegleByDepartementId(int id) {
		Departement departement = departementRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Department avec id " + id+" n'existe pas!"));
		List<Regle> regles = (List<Regle>) regleRepository.findAll();
		for(Regle rgl : regles) {
			if(rgl.getDepartements().contains(departement))
				return rgl;
		}
 		return null;
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
		Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new EntityNotFoundException("Department avec id " + departementId+" n'existe pas!"));
        Classe classe = classeRepository.findById(classeId)
        		.orElseThrow(()->new EntityNotFoundException("Classe avec id " + classeId+" n'existe pas!"));
        List<Departement> departements = (List<Departement>) departementRepository.findAll();
        for(Departement dp : departements) {
        	if(dp.getClasses().contains(classe))
        		throw new IllegalStateException("classe already assigned!");
        }
        departement.getClasses().add(classe);
        return departementRepository.save(departement);
	}

	@Override
	public Departement removeClasseFromDepartement(int departementId, int classeId) {
		Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new EntityNotFoundException("Department avec id " + departementId+" n'existe pas!"));
        Classe classe = classeRepository.findById(classeId)
        		.orElseThrow(()->new EntityNotFoundException("Classe avec id " + classeId+" n'existe pas!"));
        if(!departement.getClasses().contains(classe)) {
        	throw new IllegalStateException("classe n'appartient pas a ce departement!");
        }
        departement.getClasses().remove(classe);
		return departementRepository.save(departement);
	}

}
