package org.example.ibank.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

	private final String ID;	
	private AccountType accountType;	
	private Currency currency;	
	private float funds;
	private final List<Transaction> transactions = new ArrayList<>();


	public Account(String ID) {
        this.ID = ID;
        //TODO: get account data from database
		loadTransactionsFromDatabase();

    }
	
    // This constructor is used for testing, should not be needed in the final product
    public Account(String ID, AccountType accountType, Currency currency, float funds)
    {
    	this.ID = ID;
    	this.accountType = accountType;
    	this.currency = currency;
    	this.funds = funds;
		loadTransactionsFromDatabase();
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

	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(transactions);
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions.clear();
		if (transactions != null) {
			this.transactions.addAll(transactions);
		}
	}

	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

	private void loadTransactionsFromDatabase() {
        List<Transaction> loaded = null;
        try {
            loaded = TransactionDatabase.getTransactionsForAccount(ID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        transactions.addAll(loaded);
    }
}
