package br.com.finance.cdd.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.finance.cdd.dto.UserDTO;
import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.form.ListStringForm;
import br.com.finance.cdd.form.UserForm;
import br.com.finance.cdd.model.User;
import br.com.finance.cdd.service.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServices serviceUser;

	// Retorna UserPage de UsersForm (Lista todos os Usuários não deletados)
	@GetMapping
	public ResponseEntity<Page<UserDTO>> allUser(
			@RequestHeader(name = "id", required = true) String idAdmin,
			@RequestParam(name = "page", required = true) Integer pageNum,
			@RequestParam(name = "limit", required = true) Integer limitNum) {
		
		Long idAdminLong = Long.parseLong(idAdmin);
		Pageable page = PageRequest.of(pageNum, limitNum);

		Page<UserDTO> usersDTO = serviceUser.findAllUserDTO(page, idAdminLong);
		return new ResponseEntity<Page<UserDTO>>(usersDTO, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> oneUser(@PathVariable(name = "id") String id) {
		Long idUserLong = Long.parseLong(id);
		User user = serviceUser.findByIdUser(idUserLong);

		if (Objects.nonNull(user.getWallet()) || Objects.nonNull(user.getWallet().getDateDelete())) {
			UserDTO userDTO = new UserDTO(user);
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("User Not Found By ID: " + id);
		}
	}
	
	@GetMapping("/maxvalue")
	public ResponseEntity<ArrayList<Integer>> maxValueAppList(@RequestHeader(name = "id") String id) {
		Long idAdminLong = Long.parseLong(id);
		ArrayList<Integer> listUsersWalletValue = serviceUser.findByIdAdminMaxValueList(idAdminLong);

		return new ResponseEntity<ArrayList<Integer>>(listUsersWalletValue, HttpStatus.OK);
	}
	
	@GetMapping("/maxcountuser")
	public ResponseEntity<Integer> maxCountUser(@RequestHeader(name = "id") String id) {
		Long idAdminLong = Long.parseLong(id);
		Integer maxCountUser = serviceUser.getMaxUsers(idAdminLong);
		
		if(maxCountUser >= 0) {
			return new ResponseEntity<Integer>(maxCountUser, HttpStatus.OK);			
		} else {
			return new ResponseEntity<Integer>(0, HttpStatus.OK);			
		}

	}

	// Adiciona um novo User (Sem carteira)
	@PostMapping
	public ResponseEntity<UserForm> addUser(@Valid @RequestBody UserForm userForm) {
		serviceUser.saveUserForm(userForm);
		return new ResponseEntity<UserForm>(userForm, HttpStatus.CREATED);
	}

	// Deleta um User
	@DeleteMapping
	public ResponseEntity<UserDTO> deleteUser(@RequestParam(name = "iduser", required = true) String id) {
		Long idUserLong = Long.parseLong(id);
		UserDTO userDTO = new UserDTO(serviceUser.findByIdUser(idUserLong));
		serviceUser.deleteUserById(idUserLong);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
	
	// Deleta arrays de User
	@DeleteMapping(path = {"/deleteusers"})
	public ResponseEntity<Boolean> deleteUsers(@RequestBody @Valid ListStringForm usersId) {
		serviceUser.deleteArrayUsersById(usersId);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	// Alterar um User,
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<UserForm> updateUser(@RequestParam(name = "iduser", required = true) String id,
			@RequestBody UserForm userForm) {
		Long idUserLong = Long.parseLong(id);
		serviceUser.updateUserForm(idUserLong, userForm);
		return new ResponseEntity<UserForm>(userForm, HttpStatus.OK);
	}
}
