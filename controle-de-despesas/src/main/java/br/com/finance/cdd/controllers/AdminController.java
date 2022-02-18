package br.com.finance.cdd.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.finance.cdd.model.Admin;
import br.com.finance.cdd.service.AdminService;

@RestController
@RequestMapping("/")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private PasswordEncoder encoder;
	
	
	@PostMapping("new-admin")
	public ResponseEntity<Admin> save(@RequestBody @Valid Admin admin){
		admin.setPassword(encoder.encode(admin.getPassword()));
		adminService.save(admin);
		return new ResponseEntity<Admin>(admin, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("name/{login}")
	public ResponseEntity<Admin> getName(@PathVariable String login){
		return ResponseEntity.ok(adminService.findByLogin(login));
	}
}





