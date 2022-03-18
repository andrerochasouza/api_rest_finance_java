package br.com.finance.cdd.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.dto.UserDTO;
import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.form.ListStringForm;
import br.com.finance.cdd.form.UserForm;
import br.com.finance.cdd.model.User;
import br.com.finance.cdd.repository.AdminRepository;
import br.com.finance.cdd.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	private AdminRepository adminRepository;
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
	
	// Devolver uma lista com o usuário que mais vendeu no dia
	public UserDTO findUserByHighestValueAppDay(String date){
		Optional<List<User>> listUserOpt = userRepository.findListUserDayHighestWallet(null, null);
		return null;
	}

	// Retorna Lista de UsersDTO sem users deletados
	public Page<UserDTO> findAllUserDTO(Pageable pageable, Long idAdmin) { // Verificar o List para Page
		Page<User> users = userRepository.findByUsersPage(idAdmin, pageable);
		List<UserDTO> usersDTO = users.stream()
				.map(x -> new UserDTO(x))
				.collect(Collectors.toList());
		
		return new PageImpl<UserDTO>(usersDTO, pageable, users.getTotalElements());
	}
	
	public ArrayList<Integer> findByIdAdminMaxValueList(Long idAdmin){
		List<User> users = userRepository.findAll();
		int maxValueUserPositivo = users.stream()
							.filter(user -> user.getDateDelete() == null)
							.filter(user -> user.getWallet().getValue() >= 0)
							.collect(Collectors.toList()).size();
		
		int maxValueUserNegativo = users.stream()
				.filter(user -> user.getDateDelete() == null)
				.filter(user -> user.getWallet().getValue() < 0)
				.collect(Collectors.toList()).size();
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(maxValueUserPositivo);
		list.add(maxValueUserNegativo);
		
		return list;
	}

	// Salva um UserForm
	public void saveUserForm(UserForm userForm) {
		Integer cpf = userRepository.findCountCpf(userForm.getCpf());
		if (cpf == 0){
			User user = new User().convertToUser(userForm, adminRepository.getById(userForm.getIdAdmin()));
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
	
	// Deleta todos que estão no array
	public void deleteArrayUsersById(ListStringForm usersId) {
		List<Long> longsId = usersId.getListString().stream().map(str -> Long.parseLong(str)).collect(Collectors.toList());
		longsId.stream().forEach(id -> deleteUserById(id));
	}

	// Salva um User
	private void saveUser(User user) {
		userRepository.save(user);
	}
}
