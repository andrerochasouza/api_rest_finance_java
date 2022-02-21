package br.com.finance.cdd.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
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
		User user = userRepository.findById(id.longValue())
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found By ID: " + id));
		if (Objects.isNull(user.getDateDelete())) {
			return user;
		} else {
			throw new ResourceNotFoundException("User Not Found By ID: " + id);
		}
	}

	public User findByNameUser(String name) {
		User user = userRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found By Name: " + name));
		if (Objects.isNull(user.getDateDelete())) {
			return user;
		} else {
			throw new ResourceNotFoundException("User Not Found By Name: " + name);
		}
	}

	// Retorna Lista de UsersDTO sem users deletados
	public Page<UserDTO> findAllUserDTO(Pageable pageable) { // Verificar o List para Page
		Page<User> users = userRepository.findAllByDateDeleteIsNull(pageable);
		List<UserDTO> usersDTO = users.stream()
				.map(x -> new UserDTO(x))
				.collect(Collectors.toList());
		
		return new PageImpl<UserDTO>(usersDTO, pageable, users.getTotalElements());
	}

	// Salva um UserForm
	public void saveUserForm(UserForm userForm) {
		Integer cpf = userRepository.findCountCpf(userForm.getCpf());
		if (cpf == 0){
			User user = new User().convertToUser(userForm);
			this.saveUser(user);
			walletService.createWallet(user);
			this.saveUser(user);
			
		} else {
			User user = userRepository.findByCpf(userForm.getCpf())
									.orElseThrow(() -> new ResourceNotFoundException("User Not Found By CPF: " + cpf));
			if(Objects.isNull(user.getDateDelete())) {
				throw new ResourceNotFoundException("CPF Já Cadastrado: " + userForm.getCpf());				
			}
			
			user.setDateDelete(null);
			user.getWallet().setDateDelete(null);
			user.setName(userForm.getName());
			this.saveUser(user);
		} 
	}

	// Update um UserForm (PATCH OR PUT) (Não altera um usuário deletado)
	public void updateUserForm(Long id, UserForm userForm) {
		User user = findByIdUser(id);
		if (Objects.nonNull(userForm.getName())) {
			user.setName(userForm.getName());
		}

		if (Objects.isNull(userForm.getCpf()) || user.getCpf().equals(userForm.getCpf())) {
			this.saveUser(user);
		} else {
			Integer cpf = userRepository.findCountCpf(userForm.getCpf());
			if(cpf == 0){
				user.setCpf(userForm.getCpf());
			} else {
				throw new ResourceNotFoundException("CPF Já Cadastrado: " + userForm.getCpf());
			}
		}
		this.saveUser(user);
	}

	// Deleta um User
	public void deleteUserById(Long id) {
		User user = findByIdUser(id);
		user.setDateDelete(new Date());
		walletService.deleteWallet(user);
		this.saveUser(user);
	}

	// Salva um User
	private void saveUser(User user) {
		userRepository.save(user);
	}
}
