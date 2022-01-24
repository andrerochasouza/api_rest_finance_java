package br.com.finance.cdd.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.dto.GainDTO;
import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.model.Gain;
import br.com.finance.cdd.model.User;
import br.com.finance.cdd.repository.GainRepository;

@Service
public class GainServices {

	@Autowired
	private GainRepository gainRepository;
	@Autowired
	private UserServices userServices;

	// Salva o Gain
	public void save(Long id, Gain gain) {
		User user = userServices.findByIdUser(id);
		gain.setUser(user);
		user.setWallet(user.getWallet() + gain.getValue());
		userServices.saveUserUpdate(user);
		gainRepository.save(gain);
	}

	// Deleta o Gain
	public void delete(Long id) {
		Gain gain = findbyidGain(id);
		User user = gain.getUser();
		if (gain.getDateDelete() == null) {
			gain.setDateDelete(new Date());
			user.setWallet(user.getWallet() - gain.getValue());
			userServices.saveUserUpdate(user);
			gainRepository.save(gain);
		} else {
			throw new ResourceNotFoundException("Pay Not Found By ID: " + id);
		}
	}

	// Retorna um GainDTO pelo Id Gain
	public GainDTO findById(Long id) {
		Gain gain = findbyidGain(id);
		return new GainDTO(gain);
	}
	
	
	// Otimização de Código
	
	private Gain findbyidGain(Long id) {
		return gainRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Gain Not Found By ID: " + id));
	}
}
