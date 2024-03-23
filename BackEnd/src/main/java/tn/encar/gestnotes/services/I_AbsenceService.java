package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.Absence;

public interface I_AbsenceService {
	
    Absence saveAbsence(Absence absence);
    Absence getAbsenceById(Long id);
    List<Absence> getAllAbsence();
    void deleteAbsenceById(Long id);
}
