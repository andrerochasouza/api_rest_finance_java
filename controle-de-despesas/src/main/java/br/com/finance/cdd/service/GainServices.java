package br.com.finance.cdd.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.dto.GainDTO;
import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.model.Gain;
import br.com.finance.cdd.repository.GainRepository;

@Service
public class GainServices {

	@Autowired
	private GainRepository gainRepository;
	@Autowired
	private UserServices userServices;
	
	public void save(String name, Gain gain) {
		gain.setUser(userServices.findByNameUser(name));
		gainRepository.save(gain);
	}

	public void delete(Long id) {
		Gain gain = gainRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Gain Not Found By ID: " + id));
		gain.setDateDelete(new Date());
		gainRepository.save(gain);
	}
	
	public GainDTO findById(Long id) {
		Gain gain = gainRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Gain Not Found By ID: " + id));
		return new GainDTO(gain);
	}	
}
