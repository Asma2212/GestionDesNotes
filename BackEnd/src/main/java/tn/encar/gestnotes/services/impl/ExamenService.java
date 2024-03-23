package tn.encar.gestnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.models.entities.Examen;
import tn.encar.gestnotes.repositories.ExamenRepositoy;
import tn.encar.gestnotes.services.I_ExamenService;

@Service
public class ExamenService implements I_ExamenService{

	@Autowired
	private ExamenRepositoy examenRepository;

	@Override
	public Examen saveExamen(Examen examen) {
		return examenRepository.save(examen);
	}

	@Override
	public Examen getExamenById(Long id) {
		return examenRepository.findById(id).orElse(null);
	}

	@Override
	public List<Examen> getAllExamen() {
		return examenRepository.findAll();
	}

	@Override
	public void deleteExamenById(Long id) {
		examenRepository.deleteById(id);
	}
}

