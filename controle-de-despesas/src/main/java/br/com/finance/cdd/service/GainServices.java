package br.com.finance.cdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.model.Gain;
import br.com.finance.cdd.repository.GainRepository;

@Service
public class GainServices {

	@Autowired
	private GainRepository gainRepository;
	@Autowired
	private UserServices userServices;
	
	public void save(String name, Gain gain) {
		gain.setUser(userServices.findByName(name));
		gainRepository.save(gain);
	}
}
