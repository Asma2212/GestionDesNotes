package tn.encar.gestnotes.services.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.models.entities.Etudiant;
import tn.encar.gestnotes.models.enums.TypeFormation;
import tn.encar.gestnotes.repositories.EtudiantRepository;
import tn.encar.gestnotes.services.I_EtudiantService;

@Service
public class EtudiantService implements I_EtudiantService{

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant getEtudiantById(int id) {
        return etudiantRepository.findById(id);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return (List<Etudiant>) etudiantRepository.findAll();
    }

    @Override
    public void deleteEtudiantById(int id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public Etudiant getEtudiantByNumInscri(int numInscri) {
        return etudiantRepository.findByNumInscri(numInscri);
    }

    @Override
    public List<Etudiant> getEtudiantsByNiveau(int niveau) {
        return etudiantRepository.findByNiveau(niveau);
    }

    @Override
    public void deleteEtudiantByNumInscri(int numInscri) {
        etudiantRepository.deleteByNumInscri(numInscri);
    }

    @Override
    public int countEtudiantsByNiveau(int niveau) {
        return etudiantRepository.countByNiveau(niveau);
    }
    @Override
    public List<Etudiant> findByNiveauAndTypeFormation(String niveau, TypeFormation formation) {
        return etudiantRepository.findByNiveauAndTypeFormation(niveau, formation);
    }
    
    @Override
    public List<Etudiant> findByTypeFormation(TypeFormation formation) {
        return etudiantRepository.findByTypeFormation(formation);
    }

    
    @Override
    public int countAllEtudiants() {
        return etudiantRepository.countAllEtudiants();
    }

	@Override
    public List<Etudiant> findByAgeGreaterThan(Date dateNaiss) {
        return etudiantRepository.findByAgeGreaterThan(dateNaiss);
    }
}