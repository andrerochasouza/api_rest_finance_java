package br.com.finance.cdd.service;

import br.com.finance.cdd.dto.AplicationDTO;
import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.model.Aplication;
import br.com.finance.cdd.model.User;
import br.com.finance.cdd.repository.AplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AplicationServices {

	@Autowired
	private AplicationRepository appRepository;
	@Autowired
	private UserServices userServices;

//	 Salva o Aplication
	public void save(Long id, Aplication app) {
		User user = userServices.findByIdUser(id); // Busca usuário por ID
		user.getWallet().getAplications().add(app);

		userServices.saveUserUpdate(user);
        appRepository.save(app);
	}

	// Deleta o Pay
	public void delete(Long id) {
		Aplication app = findByIdApp(id);

		if (app.getDateDelete() == null) {
			app.setDateDelete(new Date());
			appRepository.save(app);
		} else {
			throw new ResourceNotFoundException("Pay Not Found By ID: " + id);
		}
	}

	// Retorna um PayDTO pelo Id
	public AplicationDTO findById(Long id) {
		Aplication app = findByIdApp(id);
		return new AplicationDTO(app);
	}

	// Otimizaação de código

	private Aplication findByIdApp(Long id) {
		return appRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pay Not Found By ID: " + id));
	}

}
