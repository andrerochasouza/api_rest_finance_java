package br.com.finance.cdd.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.finance.cdd.model.Admin;
import br.com.finance.cdd.repository.AdminRepository;

@RestController
@RequestMapping("/")
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private PasswordEncoder encoder;
	
	
	@PostMapping("new-admin")
	public ResponseEntity<Admin> save(@RequestBody @Valid Admin admin){
		admin.setPassword(encoder.encode(admin.getPassword()));
		return ResponseEntity.ok(adminRepository.save(admin));
	}
	
	@GetMapping("is-valid-login")
	public ResponseEntity<Boolean> isValidLogin(@RequestHeader(name = "login", required = true) String login){
		Optional<Admin> optAdmin = adminRepository.findByLogin(login);
		
		if(optAdmin.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}

	}
}





