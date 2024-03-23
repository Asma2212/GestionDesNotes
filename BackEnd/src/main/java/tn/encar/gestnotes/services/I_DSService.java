package tn.encar.gestnotes.services;

import java.util.List;

import tn.encar.gestnotes.models.entities.DS;

public interface I_DSService {

    DS saveDS(DS ds);
    DS getDSById(Long id);
    List<DS> getAllDS();
    void deleteDSById(Long id);
}
