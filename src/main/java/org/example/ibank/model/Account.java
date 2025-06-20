package org.example.ibank.model;

public class Account {

	private final String ID;	
	private AccountType accountType;	
	private Currency currency;	
	private float funds;
	
    public Account(String ID) {
        this.ID = ID;
        this.accountType = AccountType.Chequing;
        this.currency = Currency.CAD;
        this.funds = 5000;
    }
	
	public String getID() {
	    return ID;
	}

	public AccountType getAccountType() {
		return accountType;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public float getFunds() {
		return funds;
	}
	
	public void increaseFundsBy(float value) {
		funds += value;
	}
	
	public boolean tryDecreaseFundsBy(float value) {
		if (funds < value)
		{
			return false;
		}
		
		funds -= value;
		return true;
	}
}
