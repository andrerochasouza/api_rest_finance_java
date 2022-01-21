package br.com.finance.cdd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.finance.cdd.form.UserForm;
import br.com.finance.cdd.service.UserServices;

@RestController
@RequestMapping("/")
public class IndexController {

	@Autowired
	UserServices serviceUser;
	
	@GetMapping
	public ResponseEntity<List<UserForm>> indexPage(){
		List<UserForm> usersForm = serviceUser.findAllUserForm();
		return new ResponseEntity<List<UserForm>>(usersForm, HttpStatus.ACCEPTED);
	}
	
}
