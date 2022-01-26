package br.com.finance.cdd.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.dto.UserDTO;
import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.form.UserForm;
import br.com.finance.cdd.model.User;
import br.com.finance.cdd.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WalletServices walletService;

	
	// Regras de negócio



	// Retorna um User não deletado
	public User findByIdUser(Long id) {
		User user = userRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User Not Found By ID: " + id));
		if (user.getDateDelete() == null) {
			return user;
		} else {
			throw new ResourceNotFoundException("User Not Found By ID: " + id);
		}
	}

	public User findByNameUser(String name) {
		User user = userRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found By Name: " + name));
		if (user.getDateDelete() == null) {
			return user;
		} else {
			throw new ResourceNotFoundException("User Not Found By Name: " + name);
		}
	}

	// Retorna Lista de UsersDTO sem users deletados
	public Page<UserDTO> findAllUserDTO(Pageable pageable) {
		Page<User> users = userRepository.findAll(pageable);
		List<UserDTO> usersForm = users.stream()
									.filter(x -> x.getDateDelete() == null)
									.map(x -> new UserDTO(x))
									.collect(Collectors.toList());

		return new PageImpl<UserDTO>(usersForm);
	}

	// Salva um User 
		private void saveUser(User user) {
			userRepository.save(user);
		}

	// Salva um UserForm
	public void saveUserForm(UserForm userForm) {
		User user = new User().convertToUser(userForm);
		this.saveUser(user);
	}

	// Update um UserDTO (PATCH OR PUT) (Não altera um usuário deletado)
		public void updateUserForm(Long id, UserForm userForm) {
			User user = findByIdUser(id);
			user.convertToUser(userForm);
		}

	// Deleta um User
	public void deleteUserById(Long id) {
		User user = findByIdUser(id);
		user.setDateDelete(new Date());
		walletService.deleteWallet(user);
		userRepository.save(user);
	}

}
