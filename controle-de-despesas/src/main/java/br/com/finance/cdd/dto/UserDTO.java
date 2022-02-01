package br.com.finance.cdd.dto;

import java.util.Objects;

import br.com.finance.cdd.model.User;

public class UserDTO {

	private Long idUser;
	private String name;
	private Double walletValue;
	private String cpf;

	public UserDTO(User user) {
		this.idUser = user.getId();
		this.name = user.getName();
		this.walletValue = this.walletValueUser(user);
		this.cpf = user.getCpf();
	}

	public Long getIdUser() {
		return idUser;
	}

	public String getName() {
		return name;
	}

	public Double getWalletValue() {
		return walletValue;
	}

	public String getCpf() {
		return cpf;
	}

	private Double walletValueUser(User user) {
		if (Objects.isNull(user.getWallet()) || Objects.nonNull(user.getWallet().getDateDelete())) {
			return null;
		} else {
			return user.getWallet().getValue();
		}
	}
}
