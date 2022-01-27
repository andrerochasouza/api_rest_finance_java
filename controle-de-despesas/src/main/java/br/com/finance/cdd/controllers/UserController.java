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
import br.com.finance.cdd.dto.WalletDTO;
import br.com.finance.cdd.model.Aplication;
import br.com.finance.cdd.model.Wallet;
import br.com.finance.cdd.service.AplicationServices;
import br.com.finance.cdd.service.UserServices;
import br.com.finance.cdd.service.WalletServices;

@RestController
@RequestMapping("/userpage")
public class UserController {

	@Autowired
	private UserServices serviceUser;
	@Autowired
	private WalletServices serviceWallet;
	@Autowired
	private AplicationServices serviceAplication;
	
	
	// Retorna UserDTO (Informações do Usuário)
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserPageId(@PathVariable(name = "id") Long id) {
		if(serviceUser.findByIdUser(id).getWallet() ==  null) {
			UserDTO userDTO = new UserDTO(serviceUser.findByIdUser(id));
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);	
		}
		Wallet wallet = serviceWallet.findByidWallet(serviceUser.findByIdUser(id).getWallet().getId());
		WalletDTO walletDTO = new WalletDTO(wallet);
		return new ResponseEntity<WalletDTO>(walletDTO, HttpStatus.FOUND);	
	}
	
	// Criar Wallet
	@PostMapping("/{id}/add/wallet") // Ver por que não retorna o com wallet
	public ResponseEntity<?> createWallet(@PathVariable(name = "id") Long id){
		serviceWallet.createWallet(serviceUser.findByIdUser(id));
		return getUserPageId(id);
	}
	
	// Adiciona uma aplicação pelo ID do User
	@PostMapping("/{id}/add/app")
	public ResponseEntity<AplicationDTO> createApp(@PathVariable(name = "id") Long id, @Valid @RequestBody Aplication app){
		serviceAplication.save(id, app);
		AplicationDTO appDTO = new AplicationDTO(app);
		return new ResponseEntity<AplicationDTO>(appDTO, HttpStatus.CREATED);
	}
	
	
	// Deleta uma aplicação pelo Id do App
	@DeleteMapping("/delete/app/{id}")
	public ResponseEntity<AplicationDTO> deleteapp(@PathVariable(name = "id") Long id){
		serviceAplication.delete(id);
		AplicationDTO appDTO = serviceAplication.findById(id);
		return new ResponseEntity<AplicationDTO>(appDTO, HttpStatus.OK);
	}
	
	

}
