package tn.encar.gestnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.models.entities.Absence;
import tn.encar.gestnotes.repositories.AbsenceRepository;
import tn.encar.gestnotes.services.I_AbsenceService;

@Service
public class AbsenceService implements I_AbsenceService{
	
	@Autowired
	private AbsenceRepository absenceRepository ;

	@Override
	public Absence saveAbsence(Absence absence) {
		return absenceRepository.save(absence);
	}

	@Override
	public Absence getAbsenceById(Long id) {
		return absenceRepository.findById(id).orElse(null);
	}

	@Override
	public List<Absence> getAllAbsence() {
		return absenceRepository.findAll();
	}

	@Override
	public void deleteAbsenceById(Long id) {
		absenceRepository.deleteById(id);
		
	}
	
}
