package br.com.finance.cdd.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		UserDTO userDTO = new UserDTO(serviceUser.findByNameOffDelete(name));
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
			
	// Está funcionando
	@DeleteMapping("/delete/despesa/{name}/{id}")
	public ResponseEntity<PayDTO> deletePay(@PathVariable(name = "id") Long id){
		servicePay.delete(id);
		PayDTO payDTO = servicePay.findById(id);
		return new ResponseEntity<PayDTO>(payDTO, HttpStatus.OK);
	}
	
	// Está funcionando
	@DeleteMapping("/delete/receita/{name}/{id}")
	public ResponseEntity<GainDTO> deleteGain(@PathVariable(name = "id") Long id){
		serviceGain.delete(id);
		GainDTO gainDTO = serviceGain.findById(id);
		return new ResponseEntity<GainDTO>(gainDTO, HttpStatus.OK);
	}

}
