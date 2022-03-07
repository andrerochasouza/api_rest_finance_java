package br.com.finance.cdd.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.form.AplicationForm;
import br.com.finance.cdd.form.ListStringForm;
import br.com.finance.cdd.model.Aplication;
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

	// Adiciona o valor da aplicação no wallet
	public void addAppToWalletValue(Aplication app) {
		this.appToWalletValue(app, true);
	}

	// Deleta um Wallet (Também deleta as apps)
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

	// Altera Aplicação e atualiza os valores da wallet
	public void updateAppForm(Long idApp, AplicationForm appForm) {
		Aplication app = appService.findById(idApp);
		Wallet wallet = app.getWallet();

		app.setName(appForm.getName());
		app.setDescricao(appForm.getDescricao());

		switch (appForm.getTypeAplication()) {
		case RECEITA:
			wallet.setValue(wallet.getValue() + app.diffValueAppFormReceita(appForm));
			break;
		case DESPESA:
			wallet.setValue(wallet.getValue() + app.diffValueAppFormDespesa(appForm));
			break;
		}

		app.setTypeAplication(appForm.getTypeAplication());
		app.setValue(appForm.getValue());
		appService.updateApp(app);
		walletRepository.save(wallet);
	}

	// Deleta a Aplicação na wallet
	public void deleteAppToWalletTerminal(Long idApp) {
		Aplication app = appService.findById(idApp);
		appService.deleteApp(idApp);
		this.deleteAppToWalletValue(app);
	}

	// Deleta varias aplicações na wallet
	public void deleteAppsByIds(ListStringForm listAppsId) {
		List<Long> appsLongId = listAppsId.getListString().stream().map(id -> Long.parseLong(id))
				.collect(Collectors.toList());
		appsLongId.stream().forEach(id -> appService.deleteApp(id));
		appsLongId.stream().map(id -> appService.findById(id)).forEach(id -> deleteAppToWalletValue(id));
	}

	// Deleta o valor da aplicação no wallet
	public void deleteAppToWalletValue(Aplication app) {
		this.appToWalletValue(app, false);
	}

	// Otimização de código

	// Atualiza o valor do Wallet (Se a aplicação está sendo deletado ou adicionada)
	private void appToWalletValue(Aplication app, boolean typeApp) {

		Wallet wallet = app.getWallet();

		if (typeApp) {
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
