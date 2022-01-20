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

import br.com.finance.cdd.dto.GainDTO;
import br.com.finance.cdd.dto.PayDTO;
import br.com.finance.cdd.dto.UserDTO;
import br.com.finance.cdd.model.Gain;
import br.com.finance.cdd.model.Pay;
import br.com.finance.cdd.service.GainServices;
import br.com.finance.cdd.service.PayServices;
import br.com.finance.cdd.service.UserServices;

@RestController
@RequestMapping("/userpage")
public class UserController {

	@Autowired
	private UserServices serviceUser;
	@Autowired
	private PayServices servicePay;
	@Autowired
	private GainServices serviceGain;
	
	// Está funcionando
	@GetMapping("/{name}")
	public ResponseEntity<UserDTO> getUserPageName(@PathVariable(name = "name") String name) {
		UserDTO userDTO = new UserDTO(serviceUser.findByName(name));
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.FOUND);
	}
	
	// Está funcionando
	@PostMapping("/add/receita/{name}")
	public ResponseEntity<GainDTO> createGain(@PathVariable(name = "name") String name, @Valid @RequestBody Gain gain){
		serviceGain.save(name, gain);
		GainDTO gainDTO = new GainDTO(gain);
		return new ResponseEntity<GainDTO>(gainDTO, HttpStatus.CREATED);
	}
	
	// Está funcionando
	@PostMapping("/add/despesa/{name}")
	public ResponseEntity<PayDTO> createPay(@PathVariable(name = "name") String name, @Valid @RequestBody Pay pay){
		servicePay.save(name, pay);
		PayDTO payDTO = new PayDTO(pay);
		return new ResponseEntity<PayDTO>(payDTO, HttpStatus.CREATED);
	}

}
