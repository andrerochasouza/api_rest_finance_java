package br.com.finance.cdd.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.model.Aplication;
import br.com.finance.cdd.model.AplicationEnum;
import br.com.finance.cdd.model.User;
import br.com.finance.cdd.model.Wallet;
import br.com.finance.cdd.repository.WalletRepository;

@Service
public class WalletServices {

	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private AplicationServices appService;

	// Retorna um Wallet (Sem os app deletado)
	public Wallet findByidWallet(Long idWallet) {
		Wallet wallet = walletRepository.findById(idWallet)
				.orElseThrow(() -> new ResourceNotFoundException("Wallet Not Found By ID: " + idWallet));

		if (wallet.getDateDelete() == null) {
			List<Aplication> aplicationsOffDelete = wallet.getAplications().stream()
					.filter(x -> x.getDateDelete() == null).collect(Collectors.toList());
			wallet.setAplications(aplicationsOffDelete);
			return wallet;
		} else {
			throw new ResourceNotFoundException("Wallet Not Found");
		}
	}

	// Cria um Wallet (Ou retorna o dateDelete para null)
	public Wallet createWallet(User user) {
		if (user.getWallet() == null) {
			Wallet wallet = new Wallet();
			wallet.setUser(user);
			wallet.setSaldo(0.0);
			walletRepository.save(wallet);
			return wallet;
		} else {
			if (user.getWallet().getDateDelete() == null) {
				throw new ResourceNotFoundException("Wallet Found");
			} else {
				user.getWallet().setDateDelete(null);
				walletRepository.save(user.getWallet());
				return user.getWallet();
			}
		}
	}

	// Deleta um Wallet (Também deleta as APP)
	public void deleteWallet(User user) {
		if (user.getWallet() == null) {
			return;
		}

		if (user.getWallet().getDateDelete() == null) {
			if (user.getWallet().getAplications() != null) {
				user.getWallet().getAplications().stream().filter(x -> x.getDateDelete() == null)
						.forEach(x -> appService.deleteApp(x.getId()));
			}
			user.getWallet().setSaldo(0.0);
			user.getWallet().setDateDelete(new Date());
			walletRepository.save(user.getWallet());

		}
	}
	
	// Adiciona o valor no saldo
	public void addAppToWallet(Aplication app) {
		
		Wallet wallet = app.getWallet();
		if(app.getTypeAplication().equals(AplicationEnum.RECEITA)) {
			wallet.setSaldo(wallet.getSaldo() + app.getValue());
			walletRepository.save(wallet);
		} else{
			wallet.setSaldo(wallet.getSaldo() - app.getValue());
			walletRepository.save(wallet);
		}
		
	}
	
	// Adiciona o valor no saldo
	public void deleteAppToWallet(Aplication app) {
			
		Wallet wallet = app.getWallet();
		if(app.getTypeAplication().equals(AplicationEnum.RECEITA)) {
			wallet.setSaldo(wallet.getSaldo() - app.getValue());
			walletRepository.save(wallet);
		} else if(app.getTypeAplication().equals(AplicationEnum.DESPESA)){
			wallet.setSaldo(wallet.getSaldo() + app.getValue());
			walletRepository.save(wallet);
		} else {
			throw new ResourceNotFoundException("Aplication Not Found");
		}

	}
	
}
