package org.example.ibank.model;

public class Account {

	private final String ID;	
	private AccountType accountType;	
	private Currency currency;	
	private float funds;
	
    public Account(String ID) {
        this.ID = ID;
        //TODO: get account data from database
    }
	
    // This constructor is used for testing, should not be needed in the final product
    public Account(String ID, AccountType accountType, Currency currency, float funds)
    {
    	this.ID = ID;
    	this.accountType = accountType;
    	this.currency = currency;
    	this.funds = funds;
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
