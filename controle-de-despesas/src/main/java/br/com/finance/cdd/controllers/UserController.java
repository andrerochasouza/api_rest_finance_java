package br.com.finance.cdd.controllers;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.finance.cdd.dto.UserDTO;
import br.com.finance.cdd.form.UserForm;
import br.com.finance.cdd.service.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServices serviceUser;

	// Retorna UserPage de UsersForm (Lista todos os Usuários não deletados)
	@GetMapping
	public ResponseEntity<Slice<UserDTO>> indexPage(@RequestParam(name = "numpage", required = false) Integer numPage) {

		Pageable page;
		if (Objects.nonNull(numPage))
			page = PageRequest.of(numPage - 1, 5);
		else
			page = PageRequest.of(0, 5);

		Slice<UserDTO> usersDTO = serviceUser.findAllUserDTO(page);
		return new ResponseEntity<Slice<UserDTO>>(usersDTO, HttpStatus.ACCEPTED);
	}

	// Adiciona um novo User (Sem carteira)
	@PostMapping
	public ResponseEntity<UserForm> addUser(@Valid @RequestBody UserForm userForm) {
		serviceUser.saveUserForm(userForm);
		return new ResponseEntity<UserForm>(userForm, HttpStatus.CREATED);
	}

	// Deleta um User
	@DeleteMapping
	public ResponseEntity<UserDTO> deleteUser(@RequestParam(name = "iduser", required = true) Long id) {
		UserDTO userDTO = new UserDTO(serviceUser.findByIdUser(id));
		serviceUser.deleteUserById(id);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

	// Alterar um User,
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<UserForm> updateUser(@RequestParam(name = "iduser", required = true) Long id,
			@RequestBody UserForm userForm) {
		serviceUser.updateUserForm(id, userForm);
		return new ResponseEntity<UserForm>(userForm, HttpStatus.ACCEPTED);
	}
}
