package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.CC;

public interface I_CCService {

    CC saveCC(CC cc);
    CC getCCById(Long id);
    List<CC> getAllCC();
    void deleteCCById(Long id);
}
