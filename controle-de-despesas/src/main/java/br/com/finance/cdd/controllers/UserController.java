package br.com.finance.cdd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.model.entities.Pay;
import br.com.finance.cdd.model.entities.User;
import br.com.finance.cdd.model.services.PayServices;
import br.com.finance.cdd.model.services.UserServices;

@RestController
@RequestMapping("/userpage")
public class UserController {

	@Autowired
	private UserServices serviceUser;
	@Autowired
	private PayServices servicePay;
	
	@GetMapping("/{id}")
	public User userPage(@PathVariable Long id) {
		return serviceUser.findById(id);
	}
	
	@PostMapping(path = "/{id}/despesa", 
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pay> create(@PathVariable Long id, @RequestBody Pay pay) {
	    if (pay == null) {
	        throw new ResourceNotFoundException("Pay Not Found");
	    } else {
	    	servicePay.save(serviceUser.findById(id), pay);
	        return new ResponseEntity<>(pay, HttpStatus.CREATED);
	    }
	}
}
