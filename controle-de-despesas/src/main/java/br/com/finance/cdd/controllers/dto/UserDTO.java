package br.com.finance.cdd.controllers.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.finance.cdd.model.entities.User;

public class UserDTO {

	private Long id;
	private String name;
	private double wallet;

	public UserDTO() {}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.wallet = user.getWallet();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getWallet() {
		return wallet;
	}
	
	// Retorno de dados dos usuarios (protegido pelo DTO)

	public List<UserDTO> listUsersDTOconverter(List<User> users){
		return users.stream().map(UserDTO::new).collect(Collectors.toList());
	}
	
	public UserDTO userDTOConverter(User user) {
		return new UserDTO(user);
	}
	
	
}
