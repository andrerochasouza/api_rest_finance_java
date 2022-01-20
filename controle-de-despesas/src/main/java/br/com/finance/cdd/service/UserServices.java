package br.com.finance.cdd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.model.Gain;
import br.com.finance.cdd.model.Pay;
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
	
	public User findByName(String name) {
		return userRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found By Name: " + name));
	}

	public User findByNameOffDelete(String name) {
		User user =  userRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found By Name: " + name));
		
		List<Pay> pays = user.getPays().stream()
			.filter(x -> x.getDateDelete() == null)
			.collect(Collectors.toList());
		
		List<Gain> gains = user.getGains().stream()
				.filter(x -> x.getDateDelete() == null)
				.collect(Collectors.toList());
		
		user.setPays(pays);
		user.setGains(gains);
		return user;
	}
	
	
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
}
