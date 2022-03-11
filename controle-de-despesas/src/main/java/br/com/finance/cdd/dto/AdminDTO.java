package br.com.finance.cdd.dto;

import br.com.finance.cdd.model.Admin;

public class AdminDTO {
	
	private Long idAdmin;
	private String name;
	private String email;
	private String login;
	
	public AdminDTO(Admin admin) {
		this.idAdmin = admin.getId();
		this.name = admin.getName();
		this.email = admin.getEmail();
		this.login = admin.getLogin();
	}
	
	public Long getIdAdmin() {
		return idAdmin;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getLogin() {
		return login;
	}
}
