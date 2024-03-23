package tn.encar.gestnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.encar.gestnotes.models.entities.DS;
import tn.encar.gestnotes.repositories.DSRepository;
import tn.encar.gestnotes.services.I_DSService;

@Service
public class DSService implements I_DSService{

	@Autowired
	private DSRepository dsRepository ;

	@Override
	public DS saveDS(DS ds) {
		return dsRepository.save(ds);
	}

	@Override
	public DS getDSById(Long id) {
		return dsRepository.findById(id).orElse(null);
	}

	@Override
	public List<DS> getAllDS() {
		return dsRepository.findAll();
	}

	@Override
	public void deleteDSById(Long id) {
		dsRepository.deleteById(id);
		
	}
	
}
