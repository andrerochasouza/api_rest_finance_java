package br.com.finance.cdd.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String name;

	@NotBlank
	private String cpf;


	private double wallet = 0.0;

	private Date dateCreation = new Date();


	private Date dateDelete;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Pay> pays;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Gain> gains;

	public User() {
	}

	public User(String name, String cpf, double wallet, Date dateCreation,
			Date dateDelete, List<Pay> pays, List<Gain> gains) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.wallet = wallet;
		this.dateCreation = dateCreation;
		this.dateDelete = dateDelete;
		this.pays = pays;
		this.gains = gains;
	}
	

	// Getters, Setters, HashCode and equals

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
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

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
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

	public List<Pay> getPays() {
		return pays;
	}

	public void setPays(List<Pay> pays) {
		this.pays = pays;
	}

	public List<Gain> getGains() {
		return gains;
	}

	public void setGains(List<Gain> gains) {
		this.gains = gains;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		User other = (User) obj;
		return id == other.id;
	}
}
