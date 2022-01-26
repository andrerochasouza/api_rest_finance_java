package br.com.finance.cdd.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "wallets")
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private double saldo;

	private Date dateInit = new Date();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user_wallet")
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "wallet")
	private List<Aplication> aplications;

	private Date dateDelete;

	public Wallet() {
	}

	public Wallet(@NotNull double saldo, Date dateInit, User user, List<Aplication> aplications, Date dateDelete) {
		super();
		this.saldo = saldo;
		this.dateInit = dateInit;
		this.user = user;
		this.aplications = aplications;
		this.dateDelete = dateDelete;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Date getDateInit() {
		return dateInit;
	}

	public void setDateInit(Date dateInit) {
		this.dateInit = dateInit;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Aplication> getAplications() {
		return aplications;
	}

	public void setAplications(List<Aplication> aplications) {
		this.aplications = aplications;
	}

	public Date getDateDelete() {
		return dateDelete;
	}

	public void setDateDelete(Date dateDelete) {
		this.dateDelete = dateDelete;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aplications, dateDelete, dateInit, id, saldo, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wallet other = (Wallet) obj;
		return Objects.equals(aplications, other.aplications) && Objects.equals(dateDelete, other.dateDelete)
				&& Objects.equals(dateInit, other.dateInit) && id == other.id
				&& Double.doubleToLongBits(saldo) == Double.doubleToLongBits(other.saldo)
				&& Objects.equals(user, other.user);
	}

	
}
