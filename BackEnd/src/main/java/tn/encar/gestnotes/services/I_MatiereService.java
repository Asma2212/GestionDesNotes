package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.Matiere;

public interface I_MatiereService {

    Matiere saveMatiere(Matiere matiere);
    Matiere getMatiereById(Long id);
    List<Matiere> getAllMatiere();
    void deleteMatiereById(Long id);
    Matiere findByNomMatiere(String nomMatiere);
}
