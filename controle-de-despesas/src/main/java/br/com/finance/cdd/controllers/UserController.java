package br.com.finance.cdd.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.finance.cdd.model.Pay;
import br.com.finance.cdd.model.User;
import br.com.finance.cdd.service.PayServices;
import br.com.finance.cdd.service.UserServices;

@RestController
@RequestMapping("/userpage")
public class UserController {

	@Autowired
	private UserServices serviceUser;
	@Autowired
	private PayServices servicePay;
	
	// Está funcionando
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserPage(@PathVariable(name = "id") Long id) {
		User user = serviceUser.findById(id);
		return new ResponseEntity<User>(user, HttpStatus.FOUND);
	}
	
	@PostMapping("/add/despesa/{id}") //Tem que criar um DTO Pay
	public ResponseEntity<Pay> createPay(@PathVariable(name = "id") Long id, @Valid @RequestBody Pay pay){
		servicePay.save(id, pay);
		return new ResponseEntity<Pay>(pay, HttpStatus.CREATED);
	}

}
