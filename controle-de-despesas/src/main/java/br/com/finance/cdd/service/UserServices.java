package br.com.finance.cdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;

	
//	// Regras de negócio
//	
//	
//	
//	// Retorna um User não deletado
//	public User findByIdUser(Long id) {
//		User user = userRepository.findById(id)
//					.orElseThrow(() -> new ResourceNotFoundException("User Not Found By ID: " + id));
//		if (user.getDateDelete() == null) {
//			return findByIdUserOffDelete(user);
//		} else {
//			throw new ResourceNotFoundException("User Not Found By ID: " + id);
//		}
//	}
//		
//	public User findByNameUser(String name) {
//		User user = userRepository.findByName(name)
//				.orElseThrow(() -> new ResourceNotFoundException("User Not Found By Name: " + name));
//		if (user.getDateDelete() == null) {
//			return findByIdUserOffDelete(user);
//		} else {
//			throw new ResourceNotFoundException("User Not Found By Name: " + name);
//		}
//	}
//	
//	// Retorna Lista de UsersForm com users deletadas
//	public Page<UserForm> findAllUserForm(Pageable pageable) {
//		Page<User> users = userRepository.findAll(pageable);
//		List<UserForm> usersForm = users.stream()
//									.filter(x -> x.getDateDelete() == null)
//									.map(x -> new UserForm(x))
//									.collect(Collectors.toList());
//				
//		return new PageImpl<UserForm>(usersForm);
//	}
//
//	// Salva um User (Usage Update)
//		public void saveUserUpdate(User user) {
//			userRepository.save(user);
//		}
//	
//	// Salva um UserForm
//	public void saveUserForm(UserForm userForm) {
//		User user = new User();
//		user.setWallet(userForm.getWallet());
//		userToUserForm(user, userForm);
//	}
//	
//	// Update um UserForm (PATCH OR PUT) (Não altera um usuário deletado)
//		public void updateUserForm(Long id, UserForm userForm) {
//			User user = findByIdUser(id);
//			userToUserForm(user, userForm);
//		}
//
//	// Deleta um User
//	public void deleteUserById(Long id) {
//		User user = findByIdUser(id);
//		user.setDateDelete(new Date());
//		userRepository.save(user);
//	}	
//	
//	
//	// Otimização de código
//	
//	private void userToUserForm(User user, UserForm userForm) {
//		user.setCpf(userForm.getCpf());
//		user.setName(userForm.getName());
//		userRepository.save(user);
//	}
//	
//	private User findByIdUserOffDelete(User user) {
//		List<Pay> pays = user.getPays().stream().filter(x -> x.getDateDelete() == null).collect(Collectors.toList());
//		List<Gain> gains = user.getGains().stream().filter(x -> x.getDateDelete() == null).collect(Collectors.toList());
//
//		user.setPays(pays);
//		user.setGains(gains);
//		return user;
//	}

}
