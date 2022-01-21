package br.com.finance.cdd.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	
	
	// Regras de negócio
	
	// Retorna um User não deletado
	public User findByIdUser(Long id) {
		User user = userFindById(id);
		if (user.getDateDelete() == null) {
			return user;
		} else {
			throw new ResourceNotFoundException("User Not Found By ID: " + id);
		}
	}

	public User findByNameUser(String name) {
		User user = userFindByName(name);
		if (user.getDateDelete() == null) {
			return user;
		} else {
			throw new ResourceNotFoundException("User Not Found By Name: " + name);
		}
	}

	// Retorna User sem os Pays e Gains deletados
	public User findByIdUserOffDelete(Long id) {
		User user = userFindById(id);

		List<Pay> pays = user.getPays().stream().filter(x -> x.getDateDelete() == null).collect(Collectors.toList());

		List<Gain> gains = user.getGains().stream().filter(x -> x.getDateDelete() == null).collect(Collectors.toList());

		user.setPays(pays);
		user.setGains(gains);
		return user;
	}

	// Retorna Lista de UsersForm com users deletadas
	public List<UserForm> findAllUserForm() {
		List<User> users = userRepository.findAll();
		List<UserForm> usersForm = users.stream()
									.filter(x -> x.getDateDelete() == null)
									.map(x -> new UserForm(x)).collect(Collectors.toList());
		return usersForm;
	}

	// Salva um UserForm
	public void saveUserForm(UserForm userForm) {
		User user = new User();
		user.setCpf(userForm.getCpf());
		user.setName(userForm.getName());
		user.setWallet(userForm.getWallet());
		userRepository.save(user);
	}

	// Deleta um User
	public void deleteUserById(Long id) {
		User user = userFindById(id);
		if (user.getDateDelete() == null) {
			user.setDateDelete(new Date());
			userRepository.save(user);
		} else {
			throw new ResourceNotFoundException("User Not Found By ID: " + id);
		}
	}
	
	
	// Otimizaação de código
	
	private User userFindById(Long id) {
		return userRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User Not Found By ID: " + id));
	}
		
	private User userFindByName(String name) {
		return userRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found By Name: " + name));
	}
}
