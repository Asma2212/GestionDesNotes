package tn.encar.gestnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.models.entities.CC;
import tn.encar.gestnotes.repositories.CCRepository;
import tn.encar.gestnotes.services.I_CCService;

@Service
public class CCService implements I_CCService{

	@Autowired 
	private CCRepository ccRepository ;

	@Override
	public CC saveCC(CC cc) {
		return ccRepository.save(cc);
	}

	@Override
	public CC getCCById(Long id) {
		return ccRepository.findById(id).orElse(null);
	}

	@Override
	public List<CC> getAllCC() {
		return ccRepository.findAll();
	}

	@Override
	public void deleteCCById(Long id) {
		ccRepository.deleteById(id);
		
	}
}
