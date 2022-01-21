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

	public void save(String name, Pay pay) {
		pay.setUser(userServices.findByNameUser(name));
		payRepository.save(pay);
	}

	public void delete(Long id) {
		Pay pay = payRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Pay Not Found By ID: " + id));
		pay.setDateDelete(new Date());
		payRepository.save(pay);
	}

	public PayDTO findById(Long id) {
		Pay pay = payRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Pay Not Found By ID: " + id));
		return new PayDTO(pay);
	}	
}

