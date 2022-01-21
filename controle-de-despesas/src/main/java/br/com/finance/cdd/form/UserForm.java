package br.com.finance.cdd.form;

import br.com.finance.cdd.model.User;

public class UserForm {
	
	private Long id;
	private String name;
	private Double wallet;
	
	public UserForm(User user) {
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

	public Double getWallet() {
		return wallet;
	}
	
	
}
