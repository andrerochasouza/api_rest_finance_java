package br.com.finance.cdd.dto;

import br.com.finance.cdd.model.Gain;

public class GainDTO {

	private Long id;
	private String name;
	private Double value;
	private String descricao;
	
	public GainDTO(Gain gain) {
		this.id = gain.getId();
		this.name = gain.getName();
		this.value = gain.getValue();
		this.descricao = gain.getDesc();
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
	
	
}
