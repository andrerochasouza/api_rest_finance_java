package br.com.finance.cdd.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import br.com.finance.cdd.form.AplicationForm;

class AplicationTest {

	@Test
	public void diffValueAppFormReceitaPassoAppReceitaComValor300TemQueDevolVer100Negativo() {
		Aplication app = new Aplication("medico", 300.0, AplicationEnum.RECEITA, new Date(), null, null, null);
		AplicationForm appForm = new AplicationForm("Receita medico", 200.0, AplicationEnum.RECEITA, "blabla");
		
		assertEquals(-100.0, app.diffValueAppFormReceita(appForm));
	}
	
	@Test
	public void diffValueAppFormReceitaPassoAppReceitaComValor100TemQueDevolVer100() {
		Aplication app = new Aplication("medico", 100.0, AplicationEnum.RECEITA, new Date(), null, null, null);
		AplicationForm appForm = new AplicationForm("Receita medico", 200.0, AplicationEnum.RECEITA, "blabla");
		
		assertEquals(100.0, app.diffValueAppFormReceita(appForm));
	}
	
	@Test
	public void diffValueAppFormReceitaPassoAppReceitaComValor200TemQueDevolVer0() {
		Aplication app = new Aplication("medico", 200.0, AplicationEnum.RECEITA, new Date(), null, null, null);
		AplicationForm appForm = new AplicationForm("Receita medico", 200.0, AplicationEnum.RECEITA, "blabla");
		
		assertEquals(0.0, app.diffValueAppFormReceita(appForm));
	}
	
	@Test
	public void diffValueAppFormReceitaPassoAppDespesaComValor200TemQueDevolVer400() {
		Aplication app = new Aplication("medico", 200.0, AplicationEnum.DESPESA, new Date(), null, null, null);
		AplicationForm appForm = new AplicationForm("medico", 200.0, AplicationEnum.RECEITA, "blabla");
		
		assertEquals(400.0, app.diffValueAppFormReceita(appForm));
	}
	
	@Test
	public void diffValueAppFormDespesaPassoAppReceitaComValor200TemQueDevolVer400Negativo() {
		Aplication app = new Aplication("medico", 200.0, AplicationEnum.RECEITA, new Date(), null, null, null);
		AplicationForm appForm = new AplicationForm("medico", 200.0, AplicationEnum.DESPESA, "blabla");
		
		assertEquals(-400.0, app.diffValueAppFormDespesa(appForm));
	}
	
	@Test
	public void diffValueAppFormDespesaPassoAppDespesaComValor200TemQueDevolVer100Negativo() {
		Aplication app = new Aplication("medico", 200.0, AplicationEnum.DESPESA, new Date(), null, null, null);
		AplicationForm appForm = new AplicationForm("medico", 300.0, AplicationEnum.DESPESA, "blabla");
		
		assertEquals(-100.0, app.diffValueAppFormDespesa(appForm));
	}
	
	@Test
	public void diffValueAppFormDespesaPassoAppDespesaComValor300TemQueDevolVer100() {
		Aplication app = new Aplication("medico", 300.0, AplicationEnum.DESPESA, new Date(), null, null, null);
		AplicationForm appForm = new AplicationForm("medico", 200.0, AplicationEnum.DESPESA, "blabla");
		
		assertEquals(100.0, app.diffValueAppFormDespesa(appForm));
	}
	
	@Test
	public void diffValueAppFormDespesaPassoAppDespesaComValor200TemQueDevolVer0() {
		Aplication app = new Aplication("medico", 200.0, AplicationEnum.DESPESA, new Date(), null, null, null);
		AplicationForm appForm = new AplicationForm("medico", 200.0, AplicationEnum.DESPESA, "blabla");
		
		assertEquals(0.0, app.diffValueAppFormDespesa(appForm));
	}

}
