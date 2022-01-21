package br.com.finance.cdd.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.finance.cdd.form.UserForm;
import br.com.finance.cdd.service.UserServices;

@RestController
@RequestMapping("/")
public class IndexController {

	@Autowired
	private UserServices serviceUser;

	// Retorna Lista de UsersForm (Lista todos os Usuários não deletados)
	@GetMapping
	public ResponseEntity<List<UserForm>> indexPage() {
		List<UserForm> usersForm = serviceUser.findAllUserForm();
		return new ResponseEntity<List<UserForm>>(usersForm, HttpStatus.ACCEPTED);
	}

	// Adiciona um novo User
	@PostMapping
	public ResponseEntity<UserForm> addUser(@Valid @RequestBody UserForm userForm) {
		serviceUser.saveUserForm(userForm);
		return new ResponseEntity<UserForm>(userForm, HttpStatus.CREATED);
	}

	// Deleta um User
	@DeleteMapping
	public ResponseEntity<UserForm> deleteUser(@RequestParam(name = "id", required = true) Long id) {
		UserForm userForm = new UserForm(serviceUser.findByIdUser(id));
		serviceUser.deleteUserById(id);
		return new ResponseEntity<UserForm>(userForm, HttpStatus.OK);
	}

}
