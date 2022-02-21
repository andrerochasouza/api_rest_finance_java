package br.com.finance.cdd.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.form.AplicationForm;
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
		

		if (Objects.isNull(wallet.getDateDelete())) {
			List<Aplication> aplicationsOffDelete = wallet.getAplications().stream()
					.filter(x -> Objects.isNull(x.getDateDelete())).collect(Collectors.toList());
			wallet.setAplications(aplicationsOffDelete);
			return wallet;
		} else {
			throw new ResourceNotFoundException("Wallet Not Found");
		}
	}
	

	// Cria um Wallet (Ou retorna o dateDelete para null)
	public Wallet createWallet(User user) {
		if (Objects.isNull(user.getWallet())) {
			Wallet wallet = new Wallet();
			wallet.setUser(user);
			wallet.setValue(0.0);
			walletRepository.save(wallet);
			return wallet;
		} else {
			if (Objects.isNull(user.getWallet().getDateDelete())) {
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
		if (Objects.isNull(user.getWallet())) {
			return;
		}

		if (Objects.isNull(user.getWallet().getDateDelete())) {
			if (Objects.nonNull(user.getWallet().getAplications())) {
				user.getWallet().getAplications().stream().filter(x -> Objects.isNull(x.getDateDelete()))
						.forEach(x -> appService.deleteApp(x.getId()));
			}
			user.getWallet().setValue(0.0);
			user.getWallet().setDateDelete(new Date());
			walletRepository.save(user.getWallet());

		}
	}
	
	// Altera o aplication e valor da wallet
	public void updateAppForm(Long idApp, AplicationForm appForm) {
		Aplication app = appService.findById(idApp);
		Wallet wallet = app.getWallet();
		
		if(Objects.nonNull(appForm.getName())) {
			app.setName(appForm.getName());
		}
		
		if(Objects.nonNull(appForm.getDescricao())) {
			app.setDescricao(appForm.getDescricao());
		}
		
		if(Objects.nonNull(appForm.getValue())) {
			if(app.getValue() >= appForm.getValue()) {
				wallet.setValue(wallet.getValue() - (app.getValue() - appForm.getValue()));
			} else {
				wallet.setValue(wallet.getValue() + (appForm.getValue()) - app.getValue());
			}
			app.setValue(appForm.getValue());
		}
		
		if(Objects.nonNull(appForm.getTypeAplication())) {
			app.setTypeAplication(appForm.getTypeAplication());
			if(app.getTypeAplication().equals(AplicationEnum.RECEITA)) {
				wallet.setValue(wallet.getValue() + (2 * app.getValue()));
			} else {
				wallet.setValue(wallet.getValue() - (2 * app.getValue()));
			}
		}
		appService.updateApp(app);
		walletRepository.save(wallet);
	}
	
	
	// Adiciona o valor da wallet
	public void addAppToWallet(Aplication app) {
		this.AppToWallet(app, true);
	}
	
	// Deleta o valor da wallet
	public void deleteAppToWallet(Aplication app) {
		this.AppToWallet(app, false);
	}
	
	
	
	// Otimização de código
	private void AppToWallet(Aplication app, boolean typeApp) {
		
		Wallet wallet = app.getWallet();
		
		if(typeApp) {
			switch (app.getTypeAplication()) {
			case RECEITA:
				wallet.setValue(wallet.getValue() + app.getValue());
				walletRepository.save(wallet);
				break;
	
			case DESPESA:
				wallet.setValue(wallet.getValue() - app.getValue());
				walletRepository.save(wallet);
				break;
			default: 
				throw new ResourceNotFoundException("Aplication Not Found");
			}
		} else {
			switch (app.getTypeAplication()) {
			case RECEITA:
				wallet.setValue(wallet.getValue() - app.getValue());
				walletRepository.save(wallet);
				break;
				
			case DESPESA:
				wallet.setValue(wallet.getValue() + app.getValue());
				walletRepository.save(wallet);
				break;
			default: 
				throw new ResourceNotFoundException("Aplication Not Found");
			}
		}
	}
	
}
