package org.example.ibank.model;

public class CurrencyExchanger {

	public static float getExchangeRate(Currency source, Currency target) {
		// TODO: get the actual exchange rate from an api call or database 
		return 1;
	}
	
	public static float getConvertedAmount(float amount, Currency source, Currency target) {	
		return amount * getExchangeRate(source, target);
	}
	
}
