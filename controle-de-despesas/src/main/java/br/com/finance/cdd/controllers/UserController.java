package br.com.finance.cdd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.finance.cdd.service.UserServices;

@RestController
@RequestMapping("/userpage")
public class UserController {

	@Autowired
	private UserServices serviceUser;
	
//	// Retorna UserDTO (Informações do Usuário)
//	@GetMapping("/{id}")
//	public ResponseEntity<UserDTO> getUserPageId(@PathVariable(name = "id") Long id) {
//		UserDTO userDTO = new UserDTO(serviceUser.findByIdUser(id));
//		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.FOUND);	
//	}
	
	// Adiciona uma receita pelo ID do User
//	@PostMapping("/{id}/add/receita")
//	public ResponseEntity<GainDTO> createGain(@PathVariable(name = "id") Long id, @Valid @RequestBody Gain gain){
//		serviceGain.save(id, gain);
//		GainDTO gainDTO = new GainDTO(gain);
//		return new ResponseEntity<GainDTO>(gainDTO, HttpStatus.CREATED);
//	}
//	
//	// Adiciona uma despesa pelo ID do User
//	@PostMapping("/{id}/add/despesa")
//	public ResponseEntity<PayDTO> createPay(@PathVariable(name = "id") Long id, @Valid @RequestBody Pay pay){
//		servicePay.save(id, pay);
//		PayDTO payDTO = new PayDTO(pay);
//		return new ResponseEntity<PayDTO>(payDTO, HttpStatus.CREATED);
//	}
//	
//	// Deleta uma despesa pelo Id do Gain
//	@DeleteMapping("/delete/receita/{id}")
//	public ResponseEntity<GainDTO> deleteGain(@PathVariable(name = "id") Long id){
//		serviceGain.delete(id);
//		GainDTO gainDTO = serviceGain.findById(id);
//		return new ResponseEntity<GainDTO>(gainDTO, HttpStatus.OK);
//	}
//	
//	// Deleta uma despesa pelo Id do Pay
//	@DeleteMapping("/delete/despesa/{id}")
//	public ResponseEntity<PayDTO> deletePay(@PathVariable(name = "id") Long id){
//		servicePay.delete(id);
//		PayDTO payDTO = servicePay.findById(id);
//		return new ResponseEntity<PayDTO>(payDTO, HttpStatus.OK);
//	}
	

}
