package br.com.finance.cdd.dto;

import java.util.Date;

import br.com.finance.cdd.model.User;
import br.com.finance.cdd.model.Wallet;

public class UserDTO {

	private Long id;
	private String name;
	private Wallet wallet;
	private String cpf;
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.wallet = user.getWallet();
		this.cpf = user.getCpf();
	}
	

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public String getCpf() {
		return cpf;
	}
	
	public User ConverterToUser(UserDTO userDTO) {
		return new User(userDTO.getName(), userDTO.getCpf(), new Date(), null, userDTO.getWallet());
	}
	
}
