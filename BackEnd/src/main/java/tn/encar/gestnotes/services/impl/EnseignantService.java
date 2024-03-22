package tn.encar.gestnotes.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.models.entities.Classe;
import tn.encar.gestnotes.models.entities.Enseignant;
import tn.encar.gestnotes.models.enums.Statut;
import tn.encar.gestnotes.repositories.ClasseRepository;
import tn.encar.gestnotes.repositories.EnseignantRepository;
import tn.encar.gestnotes.services.I_EnseignantService;


@Service
public class EnseignantService implements I_EnseignantService{

	@Autowired
	private EnseignantRepository enseignantRepository;
	
	@Autowired
	private ClasseRepository classeRepository;
	
	public Enseignant saveEnseignant(Enseignant enseignant) {
		return enseignantRepository.save(enseignant);
	}
	
	@Override
	public List<Enseignant> getEnseignants() {
		return (List<Enseignant>) enseignantRepository.findAll();
	}

	@Override
	public Enseignant getEnseignantByPosition(Statut position) {
		return enseignantRepository.findByPosition(position);
	}
	
	@Override
	public Enseignant getEnseignantById(int id) {
		return enseignantRepository.findByid(id);
	}

	@Override
	public List<Enseignant> getEnseignantByClass(int idClass) {
		Classe classes = classeRepository.findClassById(idClass);
		return new ArrayList<>(classes.getEnseignants());
	}

	
	@Override
	public List<Enseignant> getEnseignantByLevelClass(int niveau) {
		List<Classe> classes = classeRepository.findClassesByNiveau(niveau);
	    Set<Enseignant> enseignants = new HashSet<>(); // Using a Set to avoid duplicates

	    for (Classe classe : classes) {
	        enseignants.addAll(classe.getEnseignants());
	    }

	    return new ArrayList<>(enseignants);
	}
	
	@Override
	public void addNewEnseignant(Enseignant enseignant) {
		if(!enseignantRepository.findByEmail(enseignant.getEmail()).isEmpty())
			throw new IllegalStateException("Email existe deja!");
		enseignantRepository.save(enseignant);
	}

	@Override
	public void updatePositionById(int id, Statut position) {
		Enseignant enseignant = enseignantRepository.findById(id)
				.orElseThrow(()->new IllegalStateException("Enseignant avec id "+id+" n'existe pas"));
		if(position != null && position == enseignant.getPosition() )
			enseignant.setPosition(position);
		
		enseignantRepository.save(enseignant);
	}
	
	@Override
	public void deleteEnseignantById(int id) {
		if(!enseignantRepository.existsById(id))
			throw new IllegalStateException("Enseignant avec id "+id+" n'existe pas!");
		enseignantRepository.deleteById(id);
	}

	@Override
	public int countClassOfEnseignantById(int id) {
		Enseignant enseignant = enseignantRepository.findByid(id);
	    if (enseignant != null && enseignant.getClassesAffectees() != null) {
	        return enseignant.getClassesAffectees().size();
	    }
	    return 0;
	}

	@Override
	public Enseignant assignClasseToEnseignant(int enseignantId, int classeId) {
		Enseignant enseignant = enseignantRepository.findByid(enseignantId);
		Classe classe = classeRepository.findById(classeId).get();
		enseignant.getClassesAffectees().add(classe);
		return enseignantRepository.save(enseignant);
	}

	@Override
	public Enseignant removeClasseFromEnseignant(int enseignantId, int classeId) {
		Enseignant enseignant = enseignantRepository.findByid(enseignantId);
		Classe classe = classeRepository.findById(classeId).get();
		enseignant.getClassesAffectees().remove(classe);
		return enseignantRepository.save(enseignant);
	}
	

}
