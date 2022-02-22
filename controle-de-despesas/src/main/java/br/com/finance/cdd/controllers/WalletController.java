package br.com.finance.cdd.controllers;

import java.util.Date;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/users/wallet")
public class WalletController {

	@Autowired
	private UserServices serviceUser;
	@Autowired
	private WalletServices serviceWallet;
	@Autowired
	private AplicationServices serviceAplication;

	// Retorna WalletDTO (Informações do Usuário)
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getWalletPageDTO(
			@PathVariable(name = "id") String id, 
			@RequestParam(value = "page", required = true) Integer pageNum,
			@RequestParam(value = "limit", required = true) Integer limitNum) {
		
		Long idUserLong = Long.parseLong(id);
		User user = serviceUser.findByIdUser(idUserLong);
		
		if (Objects.isNull(user.getWallet()) || Objects.nonNull(user.getWallet().getDateDelete())) {
			UserDTO userDTO = new UserDTO(user);
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		} else {
			Wallet wallet = serviceWallet.findByidWallet(user.getWallet().getId());
			Pageable page = PageRequest.of(pageNum, limitNum);

			Page<AplicationDTO> appsDTO = serviceAplication.findAllAppDTO(wallet, page);
			WalletDTO walletDTO = new WalletDTO(wallet, appsDTO);
			return new ResponseEntity<WalletDTO>(walletDTO, HttpStatus.OK);
		}
	}
	
	// Retorna AppsDTO (Informações do Usuário)
		@GetMapping(path = "/apps/{id}")
		public ResponseEntity<?> getAppsDTO(
				@PathVariable(name = "id") String id, 
				@RequestParam(value = "page", required = true) Integer pageNum,
				@RequestParam(value = "limit", required = true) Integer limitNum) {
			
			Long idUserLong = Long.parseLong(id);
			User user = serviceUser.findByIdUser(idUserLong);
			
			if (Objects.isNull(user.getWallet()) || Objects.nonNull(user.getWallet().getDateDelete())) {
				UserDTO userDTO = new UserDTO(user);
				return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
			} else {
				Wallet wallet = serviceWallet.findByidWallet(user.getWallet().getId());
				Pageable page = PageRequest.of(pageNum, limitNum);

				Page<AplicationDTO> appsDTO = serviceAplication.findAllAppDTO(wallet, page);
				return new ResponseEntity<Page<AplicationDTO>>(appsDTO, HttpStatus.OK);
			}
		}

	// Adiciona uma aplicação pelo ID do User
	@PostMapping("/{id}/add/app")
	public ResponseEntity<AplicationDTO> createApp(@PathVariable(name = "id") String idUser,
			@Valid @RequestBody AplicationForm appForm) {
		Long idUserLong = Long.parseLong(idUser);
		User user = serviceUser.findByIdUser(idUserLong);
		Wallet wallet = serviceWallet.findByidWallet(user.getWallet().getId());
		
		Aplication app = new Aplication(appForm.getName(), appForm.getValue(), appForm.getTypeAplication(), new Date(),
				wallet, null, appForm.getDescricao());
		serviceAplication.addApp(app);
		serviceWallet.addAppToWallet(app);
		AplicationDTO appDTO = new AplicationDTO(app);
		return new ResponseEntity<AplicationDTO>(appDTO, HttpStatus.CREATED);
	}

	// Deleta uma aplicação pelo Id do App
	@DeleteMapping("/{id}/delete/app")
	public ResponseEntity<AplicationDTO> deleteApp(@RequestParam(value = "idapp", required = true) String idApp) {
		Long idAppLong = Long.parseLong(idApp);
		serviceAplication.deleteApp(idAppLong);
		Aplication app = serviceAplication.findById(idAppLong);
		serviceWallet.deleteAppToWallet(app);
		AplicationDTO appDTO = serviceAplication.findByIdDTO(idAppLong);
		return new ResponseEntity<AplicationDTO>(appDTO, HttpStatus.OK);
	}
	
	// Altera uma aplicação pelo Id do App
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH }, path = "/{id}")
	public ResponseEntity<AplicationForm> updateApp(@RequestParam(name = "idapp", required = true) String idApp,
			@RequestBody AplicationForm appForm) {
		Long idAppLong = Long.parseLong(idApp);
		serviceWallet.updateAppForm(idAppLong, appForm);
		return new ResponseEntity<AplicationForm>(appForm, HttpStatus.ACCEPTED);
	}

}
