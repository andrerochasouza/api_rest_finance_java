package br.com.finance.cdd.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.finance.cdd.model.Wallet;

public class WalletDTO {

	private Long idUser;
	private Double walletValue;
	private String nameUser;
	private List<AplicationDTO> aplicationsDTO;
	
	
	public WalletDTO(Wallet wallet) {
		this.idUser = wallet.getUser().getId();
		this.walletValue = wallet.getSaldo();
		this.nameUser = wallet.getUser().getName();
		this.aplicationsDTO = this.convertToListAplicationDTO(wallet);
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
	
	private List<AplicationDTO> convertToListAplicationDTO(Wallet wallet){
		return wallet.getAplications().stream()
				 .filter(x -> x.getDateDelete() == null)
				 .map(x -> new AplicationDTO(x)).collect(Collectors.toList());
	}
	
}
