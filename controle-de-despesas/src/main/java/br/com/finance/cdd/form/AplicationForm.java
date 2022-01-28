package br.com.finance.cdd.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.finance.cdd.model.AplicationEnum;

public class AplicationForm {

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@Min(0)
	private Double value;

	@NotNull
	private AplicationEnum typeAplication;
	private String descricao;

	public AplicationForm(String name, Double value, AplicationEnum typeAplication, String descricao) {
		this.name = name;
		this.value = value;
		this.typeAplication = typeAplication;
		this.descricao = descricao;
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
