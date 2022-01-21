package br.com.finance.cdd.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.dto.UserDTO;
import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.form.UserForm;
import br.com.finance.cdd.model.Gain;
import br.com.finance.cdd.model.Pay;
import br.com.finance.cdd.model.User;
import br.com.finance.cdd.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;

	public User findByIdUser(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found By ID: " + id));
	}
	
	public User findByNameUser(String name) {
		return userRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found By Name: " + name));
	}

	public User findByNameUserOffDelete(String name) {
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

	public List<UserForm> findAllUserForm() {
		List<User> users = userRepository.findAll();
		List<UserForm> usersForm = users.stream()
									.map(x -> new UserForm(x))
									.collect(Collectors.toList());
		return usersForm;
	}
}
