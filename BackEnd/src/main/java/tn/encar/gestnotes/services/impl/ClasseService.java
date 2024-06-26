package tn.encar.gestnotes.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Departement;
import tn.encar.gestnotes.models.entities.Enseignant;
import tn.encar.gestnotes.repositories.ClasseRepository;
import tn.encar.gestnotes.repositories.DepartementRepository;
import tn.encar.gestnotes.repositories.EnseignantRepository;
import tn.encar.gestnotes.services.I_ClasseService;


@Service
public class ClasseService implements I_ClasseService{
	
	@Autowired
	private ClasseRepository classeRepository;
	
	@Autowired
	private EnseignantRepository enseignantRepository;
	
	@Autowired
	private DepartementRepository departementRepository;
	
	@Override
	public List<Classe> getClasses() {
		return (List<Classe>) classeRepository.findAll();
	}

	@Override
	public Classe getClassseById(int id) {
		return classeRepository.findClassById(id);
	}

	@Override
	public List<Classe> getClassesByNiveau(int niveau) {
		return classeRepository.findClassesByNiveau(niveau);
	}

	@Override
	public List<Classe> getClassesByIdEnseignant(int id) {
		Enseignant enseignant = enseignantRepository.findById(id)
				.orElseThrow(()->new IllegalStateException("Enseignant avec id "+id+" n'existe pas"));
		Set<Classe> classes = enseignant.getClassesAffectees();
		return  new ArrayList<>(classes);
	}
	
	@Override
	public Departement getDepartementByClasseId(int classeId) {
		Classe classe = classeRepository.findById(classeId)
				.orElseThrow(()->new IllegalStateException("classe avec id "+classeId+"n'existe pas!"));
		List<Departement> departements = (List<Departement>) departementRepository.findAll();
		for(Departement dp : departements){
			if(dp.getClasses().contains(classe))
				return dp;
		}
		return null;
	}

	@Override
	public void addNewClassse(Classe classe) {
		if(!classeRepository.findClassesByNiveauAndGroupe(classe.getNiveau(), classe.getGroupe()).isEmpty())
			throw new IllegalStateException("classe existe deja!");
		
		classeRepository.save(classe);
	}

	@Override
	public void deleteClasseById(int id) {
		if(classeRepository.findClassById(id) == null)
			throw new IllegalStateException("classe avec id "+id+" n'existe pas");
		classeRepository.deleteById(id);
	}

	@Override
	public int countClasseByNiveau(int niveau) {
		return classeRepository.countByNiveau(niveau);
	}

}
