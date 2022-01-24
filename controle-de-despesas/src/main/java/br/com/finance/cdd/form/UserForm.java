package br.com.finance.cdd.form;

import javax.validation.constraints.NotBlank;

import br.com.finance.cdd.model.User;

public class UserForm {
	
	private Long id;
	
	@NotBlank
	private String name;
	
	private Double wallet = 0.0;
	
	@NotBlank
	private String cpf;
	
	public UserForm() {
	}
	
	public UserForm(User user) {
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

	public Double getWallet() {
		return wallet;
	}
	
	public String getCpf() {
		return cpf;
	}		
}
