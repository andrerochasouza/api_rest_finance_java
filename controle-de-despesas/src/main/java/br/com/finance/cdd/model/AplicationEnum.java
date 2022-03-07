package br.com.finance.cdd.model;

public enum AplicationEnum {

	RECEITA{
		@Override
		public double typeAplicationValue(Double value) {
			return value;
		}
	}, 
	DESPESA {
		@Override
		public double typeAplicationValue(Double value) {
			return value*(-1);
		}
	};
	
	public abstract double typeAplicationValue(Double value);
}
