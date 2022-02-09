package br.com.finance.cdd.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@Email
	@Column(unique = true)
	@NotNull
	private String email;
	
	@Column(unique = true)
	private String login;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String pass;

	public Admin() {
	}
	
	public Admin(String name, @Email String email, String login, String pass) {
		this.name = name;
		this.email = email;
		this.login = login;
		this.pass = pass;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, login, name, pass);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(login, other.login)
				&& Objects.equals(name, other.name) && Objects.equals(pass, other.pass);
	}
	
	
}
