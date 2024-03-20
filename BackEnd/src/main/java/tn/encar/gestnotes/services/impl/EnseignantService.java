package tn.encar.gestnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.models.entities.Enseignant;
import tn.encar.gestnotes.models.enums.Statut;
import tn.encar.gestnotes.repositories.EnseignantRepository;
import tn.encar.gestnotes.services.I_EnseignantService;


@Service
public class EnseignantService implements I_EnseignantService{

	@Autowired
	private EnseignantRepository enseignantRepository;
	
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
		return enseignantRepository.findById1(id);
	}

	@Override
	public List<Enseignant> getEnseignantByClass(int idClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enseignant> getEnseignantByLevelClass(int niveau) {
		// TODO Auto-generated method stub
		return null;
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
				.orElseThrow(()->new IllegalThreadStateException("Enseignant avec id "+id+" n'existe pas"));
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
		// TODO Auto-generated method stub
		return 0;
	}

	

	

}
