package br.com.finance.cdd.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.dto.PayDTO;
import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.model.Pay;
import br.com.finance.cdd.repository.PayRepository;

@Service
public class PayServices {

	@Autowired
	private PayRepository payRepository;
	@Autowired
	private UserServices userServices;

	// Salva o Pay
	public void save(Long id, Pay pay) {
		pay.setUser(userServices.findByIdUser(id));
		payRepository.save(pay);
	}

	// Deleta o Pay
	public void delete(Long id) {
		Pay pay = findByIdPay(id);
		if (pay.getDateDelete() == null) {
			pay.setDateDelete(new Date());
			payRepository.save(pay);
		} else {
			throw new ResourceNotFoundException("Pay Not Found By ID: " + id);
		}
	}

	// Retorna um PayDTO pelo Id
	public PayDTO findById(Long id) {
		Pay pay = findByIdPay(id);
		return new PayDTO(pay);
	}
	
	
	// Otimizaação de código]
	
	private Pay findByIdPay(Long id) {
		return payRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pay Not Found By ID: " + id));
	}
}
