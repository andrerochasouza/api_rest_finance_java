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

import br.com.finance.cdd.dto.AplicationDTO;
import br.com.finance.cdd.dto.UserDTO;
import br.com.finance.cdd.model.Aplication;
import br.com.finance.cdd.service.UserServices;

@RestController
@RequestMapping("/userpage")
public class UserController {

	@Autowired
	private UserServices serviceUser;
	
	@Autowired
	private AplicationServices serviceAplication;
	
	// Retorna UserDTO (Informações do Usuário)
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserPageId(@PathVariable(name = "id") Long id) {
		UserDTO userDTO = new UserDTO(serviceUser.findByIdUser(id));
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.FOUND);	
	}
	
	// Adiciona uma receita pelo ID do User
	@PostMapping("/{id}/add/app")
	public ResponseEntity<AplicationDTO> createGain(@PathVariable(name = "id") Long id, @Valid @RequestBody Aplication app){
		serviceAplication.save(id, app);
		AplciationDTO appDTO = new AplicationDTO(app);
		return new ResponseEntity<AplicationDTO>(appDTO, HttpStatus.CREATED);
	}
	
	
	// Deleta uma despesa pelo Id do Gain
	@DeleteMapping("/delete/app/{id}")
	public ResponseEntity<AplicationDTO> deleteGain(@PathVariable(name = "id") Long id){
		serviceAplication.delete(id);
		AplicationDTO appDTO = serviceAplication.findById(id);
		return new ResponseEntity<AplicationDTO>(appDTO, HttpStatus.OK);
	}
	
	

}
