package br.com.finance.cdd.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.finance.cdd.form.AplicationForm;
import br.com.finance.cdd.model.AplicationEnum;
import br.com.finance.cdd.model.Wallet;

class WalletServicesTest {

	@Test
	public void walletComValor1000ComUmaNovaReceitaNegativa() {
		Wallet wallet = new Wallet(1000.0, null, null, null, null);
		AplicationForm appForm = new AplicationForm("Receita medico", 100.0, AplicationEnum.RECEITA, "blabla");
		wallet.setValue(wallet.getValue() + (appForm.getValue()*(-1)));
		
		assertEquals(900.0, wallet.getValue());
	}

}
