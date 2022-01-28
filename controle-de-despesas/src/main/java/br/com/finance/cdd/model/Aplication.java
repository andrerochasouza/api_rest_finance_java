package br.com.finance.cdd.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "aplications")
public class Aplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String name;

	@Min(0)
	@NotNull
	private Double value;

	@Enumerated(EnumType.STRING)
	private AplicationEnum typeAplication;

	private Date dateInit = new Date();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_wallet_aplication")
	private Wallet wallet;

	private Date dateDelete;

	private String descricao;

	public Aplication() {
	}

	public Aplication(@NotBlank String name, @Min(0) @NotNull double value, AplicationEnum typeAplication,
			Date dateInit, Wallet wallet, Date dateDelete, String descricao) {
		super();
		this.name = name;
		this.value = value;
		this.typeAplication = typeAplication;
		this.dateInit = dateInit;
		this.wallet = wallet;
		this.dateDelete = dateDelete;
		this.descricao = descricao;
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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public AplicationEnum getTypeAplication() {
		return typeAplication;
	}

	public void setTypeAplication(AplicationEnum typeAplication) {
		this.typeAplication = typeAplication;
	}

	public Date getDateInit() {
		return dateInit;
	}

	public void setDateInit(Date dateInit) {
		this.dateInit = dateInit;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public Date getDateDelete() {
		return dateDelete;
	}

	public void setDateDelete(Date dateDelete) {
		this.dateDelete = dateDelete;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aplication other = (Aplication) obj;
		return id == other.id;
	}

}
