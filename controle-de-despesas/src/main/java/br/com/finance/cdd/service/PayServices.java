package br.com.finance.cdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.model.Pay;
import br.com.finance.cdd.repository.PayRepository;

@Service
public class PayServices {

	@Autowired
	private PayRepository payRepository;
	@Autowired
	private UserServices userServices;

	public void save(Long id, Pay pay) {
		pay.setUser(userServices.findById(id));
		payRepository.save(pay);
	}	
}

