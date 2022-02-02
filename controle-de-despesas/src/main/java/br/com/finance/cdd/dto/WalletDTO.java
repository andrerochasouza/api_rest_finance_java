package br.com.finance.cdd.dto;

import java.util.List;

import br.com.finance.cdd.model.Wallet;

public class WalletDTO {

	private Long idUser;
	private Double walletValue;
	private String nameUser;
	private List<AplicationDTO> aplicationsDTO;

	public WalletDTO(Wallet wallet, List<AplicationDTO> appsDTO) {
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

	public List<AplicationDTO> getAplicationsDTO() {
		return aplicationsDTO;
	}

}
