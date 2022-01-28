package br.com.finance.cdd.dto;

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
		if (user.getWallet() == null || user.getWallet().getDateDelete() != null) {
			return null;
		} else {
			return user.getWallet().getSaldo();
		}
	}
}
