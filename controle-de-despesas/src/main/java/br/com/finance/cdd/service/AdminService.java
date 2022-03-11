package br.com.finance.cdd.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.dto.AdminDTO;
import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.model.Admin;
import br.com.finance.cdd.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	// Salva um admin
	public void save(@Valid Admin admin) {
		adminRepository.save(admin);
	}
	
	// Achar o login e retorna Admin
	public AdminDTO findByLogin(String login) {
		Optional<Admin> admin = adminRepository.findByLogin(login);
		if (admin.isEmpty()) {
			throw new ResourceNotFoundException("Admin Not Found By Login: " + login);
		} else {
			return new AdminDTO(admin.get());
		}
	}
}
