package br.com.finance.cdd.dto;

import java.util.Date;

import br.com.finance.cdd.model.Pay;

public class PayDTO {

	private Long id;
	private String name;
	private Double value;
	private String descricao;
	private Date datePay;
	
	
	public PayDTO(Pay pay) {
		this.id = pay.getId();
		this.name = pay.getName();
		this.value = pay.getValue();
		this.descricao = pay.getDesc();
		this.datePay = pay.getDatePay();
	}


	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public Double getValue() {
		return value;
	}


	public String getDescricao() {
		return descricao;
	}


	public Date getDatePay() {
		return datePay;
	}
	
}
