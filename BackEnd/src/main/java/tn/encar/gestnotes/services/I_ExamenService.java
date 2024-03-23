package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.Examen;

public interface I_ExamenService {
	Examen saveExamen(Examen examen);
    Examen getExamenById(Long id);
    List<Examen> getAllExamen();
    void deleteExamenById(Long id);
}
