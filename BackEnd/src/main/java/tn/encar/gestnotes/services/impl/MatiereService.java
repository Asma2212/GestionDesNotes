package tn.encar.gestnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.models.entities.Matiere;
import tn.encar.gestnotes.repositories.MatiereRepository;
import tn.encar.gestnotes.services.I_MatiereService;

@Service
public class MatiereService implements I_MatiereService{

	@Autowired
	private MatiereRepository matiereRepository ;

	@Override
	public Matiere saveMatiere(Matiere matiere) {
		return matiereRepository.save(matiere);
	}

	@Override
	public Matiere getMatiereById(Long id) {
		return matiereRepository.findById(id).orElse(null);
	}

	@Override
	public List<Matiere> getAllMatiere() {
		return matiereRepository.findAll();
	}

	@Override
	public void deleteMatiereById(Long id) {
		matiereRepository.deleteById(id);
	}
	
	@Override
	public Matiere findByNomMatiere(String nomMatiere) {
		return matiereRepository.findByNomMatiere(nomMatiere);
	}
}
