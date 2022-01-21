package br.com.finance.cdd.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.finance.cdd.model.User;

public class UserDTO {

	private Long id;
	private String name;
	private Double wallet;
	private List<PayDTO> paysDTO;
	private List<GainDTO> gainsDTO;
	

	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.wallet = user.getWallet();
		this.paysDTO = user.getPays().stream().map(x -> new PayDTO(x)).collect(Collectors.toList());
		this.gainsDTO = user.getGains().stream().map(x -> new GainDTO(x)).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getWallet() {
		return wallet;
	}

	public List<PayDTO> getPaysDTO() {
		return paysDTO;
	}

	public List<GainDTO> getGainsDTO() {
		return gainsDTO;
	}
	
}
