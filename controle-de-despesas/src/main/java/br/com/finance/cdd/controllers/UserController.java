package br.com.finance.cdd.controllers;

import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.finance.cdd.dto.AplicationDTO;
import br.com.finance.cdd.dto.UserDTO;
import br.com.finance.cdd.dto.WalletDTO;
import br.com.finance.cdd.form.AplicationForm;
import br.com.finance.cdd.model.Aplication;
import br.com.finance.cdd.model.User;
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
		User user = serviceUser.findByIdUser(id);
		if (user.getWallet() == null || user.getWallet().getDateDelete() != null) {
			UserDTO userDTO = new UserDTO(user);
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
		} else {
			Wallet wallet = serviceWallet.findByidWallet(user.getWallet().getId());
			WalletDTO walletDTO = new WalletDTO(wallet);
			return new ResponseEntity<WalletDTO>(walletDTO, HttpStatus.FOUND);
		}
	}

	// Criar Wallet
	@PostMapping("/{id}/add/wallet")
	public ResponseEntity<?> createWallet(@PathVariable(name = "id") Long id) {
		Wallet wallet = serviceWallet.createWallet(serviceUser.findByIdUser(id));
		WalletDTO walletDTO = new WalletDTO(wallet);
		return new ResponseEntity<WalletDTO>(walletDTO, HttpStatus.ACCEPTED);
	}

	// Deletar Wallet
	@DeleteMapping("/{id}/delete/wallet")
	public ResponseEntity<?> deleteWallet(@PathVariable(name = "id") Long id) {
		serviceWallet.deleteWallet(serviceUser.findByIdUser(id));
		return getUserPageId(id);
	}

	// Adiciona uma aplicação pelo ID do User
	@PostMapping("/{id}/add/app")
	public ResponseEntity<AplicationDTO> createApp(@PathVariable(name = "id") Long idUser,
			@Valid @RequestBody AplicationForm appForm) {
		User user = serviceUser.findByIdUser(idUser);
		Wallet wallet = serviceWallet.findByidWallet(user.getWallet().getId());
		Aplication app = new Aplication(appForm.getName(), appForm.getValue(), appForm.getTypeAplication(), new Date(),
				wallet, null, appForm.getDescricao());
		serviceAplication.addApp(app);
		AplicationDTO appDTO = new AplicationDTO(app);
		return new ResponseEntity<AplicationDTO>(appDTO, HttpStatus.CREATED);
	}

	// Deleta uma aplicação pelo Id do App
	@DeleteMapping("{id}/delete/app")
	public ResponseEntity<AplicationDTO> deleteapp(@RequestParam(value = "idapp") Long idApp) {
		serviceAplication.deleteApp(idApp);
		AplicationDTO appDTO = serviceAplication.findById(idApp);
		return new ResponseEntity<AplicationDTO>(appDTO, HttpStatus.OK);
	}

}
