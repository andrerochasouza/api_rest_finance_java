package br.com.finance.cdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.finance.cdd.form.AplicationForm;
import br.com.finance.cdd.model.Aplication;
import br.com.finance.cdd.model.AplicationEnum;
import br.com.finance.cdd.model.User;
import br.com.finance.cdd.model.Wallet;

class WalletServicesTest {

	// Atributos para testes
	private Aplication appContaLuz;
	private Aplication appBonusEmpresa;
	private List<Aplication> apps;
	private Wallet wallet;
	private WalletServices service;
	
	
// 	Valores dos atributos de testes
	
	@BeforeEach
	public void inicializar() {
		this.appContaLuz = new Aplication("conta luz", 1000.0, AplicationEnum.DESPESA, new Date(), null, null, "conta de luz mensal");
		this.appBonusEmpresa = new Aplication("bonus", 1000.0, AplicationEnum.RECEITA, new Date(), null, null, "bonus empresa");
		this.apps.add(appContaLuz);
		this.apps.add(appBonusEmpresa);
		this.wallet = new Wallet(0.0, new Date(), new User(), apps, null);
	}
	
	
	// Input AplicationForm: Name - (bonus)
	//						 Value - (500)
	//                       TypeAplcation - (Receita)
	//                       Descricao - (bonus mensal)
	//
	// Output Value - (-500)
	@Test
	void updateAppFormTest1() {
		AplicationForm appForm = new AplicationForm("bonus 2", 500.0, AplicationEnum.RECEITA, "bunus empresa mensal");
		double value = service.appReceitaUpdate(appForm, appBonusEmpresa);
		
		assertEquals(-500.0, value);		
	}

}
