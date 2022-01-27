package br.com.finance.cdd.dto;

import br.com.finance.cdd.model.Aplication;
import br.com.finance.cdd.model.AplicationEnum;

public class AplicationDTO {

	private Long idApp;
	private String name;
	private Double value;
	private AplicationEnum typeAplication;
	private String descricao;
	
	public AplicationDTO(Aplication aplication) {
		this.idApp = aplication.getId();
		this.name = aplication.getName();
		this.value = aplication.getValue();
		this.typeAplication = aplication.getTypeAplication();
		this.descricao = aplication.getDescricao();
	}

	public Long getIdApp() {
		return idApp;
	}

	public String getName() {
		return name;
	}

	public Double getValue() {
		return value;
	}
	
	public AplicationEnum getTypeAplication() {
		return typeAplication;
	}

	public String getDescricao() {
		return descricao;
	}
}
