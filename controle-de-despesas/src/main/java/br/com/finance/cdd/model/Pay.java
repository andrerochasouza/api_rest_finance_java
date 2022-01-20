package br.com.finance.cdd.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Pay {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String name;
	
	@NotNull
	@Min(0)
	private double value;
	
	private Date datePay;
	
	private Date dateInit = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user_pay")
	private User user;
	
	private Date dateDelete;
	
	private String descricao;

	public Pay() {
	}

	public Pay( String name, double value, Date datePay, Date dateInit, User user,
			Date dateDelete, String desc) {
		super();
		this.name = name;
		this.value = value;
		this.datePay = datePay;
		this.dateInit = dateInit;
		this.user = user;
		this.dateDelete = dateDelete;
		this.descricao = desc;
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

	public Date getDatePay() {
		return datePay;
	}

	public void setDatePay(Date datePay) {
		this.datePay = datePay;
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

	public Date getDateDelete() {
		return dateDelete;
	}

	public void setDateDelete(Date dateDelete) {
		this.dateDelete = dateDelete;
	}

	public String getDesc() {
		return descricao;
	}

	public void setDesc(String desc) {
		this.descricao = desc;
	}


}
