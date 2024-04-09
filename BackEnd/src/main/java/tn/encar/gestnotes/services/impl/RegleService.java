package tn.encar.gestnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.models.entities.Departement;
import tn.encar.gestnotes.models.entities.Regle;
import tn.encar.gestnotes.repositories.DepartementRepository;
import tn.encar.gestnotes.repositories.RegleRepository;
import tn.encar.gestnotes.services.I_RegleService;


@Service
public class RegleService implements I_RegleService{

	@Autowired
	private RegleRepository regleRepository;
	
	@Autowired
	private DepartementRepository departementRepository;
	
	@Override
	public Regle getRegleById(int id) {
		return regleRepository.findById(id)
				.orElseThrow(()->new IllegalStateException("Regle n'existe pas!"));
	}

	@Override
	public List<Regle> getAllRegles() {
		return (List<Regle>) regleRepository.findAll();
	}
	
	@Override
	public Regle saveRegle(Regle regle) {
		return regleRepository.save(regle);
	}

	@Override
	public void deleteRegle(int id) {
		if(!regleRepository.existsById(id))
			throw new IllegalStateException("Regle avec id: "+id+" n'existe pas!");
		regleRepository.deleteById(id);
	}

	@Override
	public void assignRegelToDepartement(int idRegle, int idDepartement) {
		Regle regle = regleRepository.findById(idRegle)
				.orElseThrow(()->new IllegalStateException("Regle n'existe pas!"));
		Departement departement = departementRepository.findById(idDepartement)
				.orElseThrow(()->new IllegalStateException("Regle n'existe pas!"));
		regle.getDepartements().add(departement);
		regleRepository.save(regle);
		
	}

	@Override
	public void removeRegleFromDepartement(int idRegle, int idDepartement) {
		Regle regle = regleRepository.findById(idRegle)
				.orElseThrow(()->new IllegalStateException("Regle n'existe pas!"));
		Departement departement = departementRepository.findById(idDepartement)
				.orElseThrow(()->new IllegalStateException("Regle n'existe pas!"));
		
		regle.getDepartements().remove(departement);
		regleRepository.save(regle);
		
	}

}
