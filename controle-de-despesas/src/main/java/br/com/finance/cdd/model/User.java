package br.com.finance.cdd.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.finance.cdd.form.UserForm;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String name;

	@NotBlank
	@Length(min = 14 , max = 14)
	private String cpf;

	private Date dateCreation = new Date();

	private Date dateDelete = null;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private Wallet wallet;

	public User() {
	}

	public User(@NotBlank String name, @NotBlank String cpf, Date dateCreation, Date dateDelete, Wallet wallet) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.dateCreation = dateCreation;
		this.dateDelete = dateDelete;
		this.wallet = wallet;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateDelete() {
		return dateDelete;
	}

	public void setDateDelete(Date dateDelete) {
		this.dateDelete = dateDelete;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, dateCreation, dateDelete, id, name, wallet);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dateCreation, other.dateCreation)
				&& Objects.equals(dateDelete, other.dateDelete) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(wallet, other.wallet);
	}

	public User convertToUser(UserForm userForm) {
		return new User(userForm.getName(), userForm.getCpf(), getDateCreation(), getDateDelete(), null);
	}
}
