package br.com.finance.cdd.form;

import javax.validation.constraints.NotNull;

public class UserForm {

	@NotNull
	private String name;
	
	@NotNull
	private String cpf;

	@NotNull
	private Long idAdmin;


	public UserForm(@NotNull String name, @NotNull String cpf, @NotNull Long idAdmin) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.idAdmin = idAdmin;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Long getIdAdmin() {
		return idAdmin;
	}
}
