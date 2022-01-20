package br.com.finance.cdd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.model.User;
import br.com.finance.cdd.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;

	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found By ID: " + id));
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
}
