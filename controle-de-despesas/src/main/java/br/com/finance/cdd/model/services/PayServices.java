package br.com.finance.cdd.model.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.model.entities.Pay;
import br.com.finance.cdd.model.entities.User;
import br.com.finance.cdd.model.repository.PayRepository;

@Service
public class PayServices {

	@Autowired
	PayRepository payRepository;

	public void save(User user, Pay pay) {

		try {
			Date atualDate = new Date();
			
			if (pay.getDatePay().after(atualDate)) {
				pay.setUser(user);
				payRepository.save(pay);
			} 
		} catch (Exception e) {
			throw new ResourceNotFoundException("/nMore information: " + e);
		}
		
	}
	
	
}
