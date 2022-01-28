package br.com.finance.cdd.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserForm {

	@NotNull
	@NotBlank
	private String name;
	@NotNull
	@NotBlank
	private String cpf;

	public UserForm(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

}
