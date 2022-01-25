package br.com.finance.cdd.dto;

import java.util.Date;

import br.com.finance.cdd.model.Aplication;
import br.com.finance.cdd.model.AplicationEnum;
import br.com.finance.cdd.model.Wallet;

public class AplicationDTO {

	private Long id;
	private String name;
	private Double value;
	private Wallet wallet;
	private AplicationEnum typeAplication;
	private String descricao;
	
	public AplicationDTO(Aplication aplication) {
		this.id = aplication.getId();
		this.name = aplication.getName();
		this.value = aplication.getValue();
		this.wallet = aplication.getWallet();
		this.typeAplication = aplication.getTypeAplication();
		this.descricao = aplication.getDescricao();
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
	
	public Wallet getWallet() {
		return wallet;
	}

	public AplicationEnum getTypeAplication() {
		return typeAplication;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public Aplication converterToAplication(AplicationDTO aplicationDTO) {
		return new Aplication(
					aplicationDTO.getName(), aplicationDTO.getValue(), 
					aplicationDTO.getTypeAplication(),new Date(), aplicationDTO.getWallet(), 
					null, aplicationDTO.getDescricao());
						
					
	}
	
}
