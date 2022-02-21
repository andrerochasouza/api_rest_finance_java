package br.com.finance.cdd.dto;

import org.springframework.data.domain.Page;

import br.com.finance.cdd.model.Wallet;

public class WalletDTO {

	private Long idUser;
	private Double walletValue;
	private String nameUser;
	private Page<AplicationDTO> aplicationsDTO;

	public WalletDTO(Wallet wallet, Page<AplicationDTO> appsDTO) {
		this.idUser = wallet.getUser().getId();
		this.walletValue = wallet.getValue();
		this.nameUser = wallet.getUser().getName();
		this.aplicationsDTO = appsDTO;
	}

	public Long getIdUser() {
		return idUser;
	}

	public Double getWalletValue() {
		return walletValue;
	}

	public String getNameUser() {
		return nameUser;
	}

	public Page<AplicationDTO> getAplicationsDTO() {
		return aplicationsDTO;
	}

}
