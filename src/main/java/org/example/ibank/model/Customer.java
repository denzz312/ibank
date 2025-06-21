package org.example.ibank.model;

public class Customer {

	public Account[] accounts;
	public final String cardNumber;
	
	public Customer(String cardNumber) {
		this.cardNumber = cardNumber;
		//TODO: set card numbers from database
	}
	
	public Customer(String cardNumber, Account[] accounts)
	{
		this.cardNumber = cardNumber;
		this.accounts = accounts;
	}
	
	public boolean tryTransferFunds (float amount, Account source, Account target) {
		
		if (!source.tryDecreaseFundsBy(amount))
		{
			return false;
		}
		
		target.increaseFundsBy(CurrencyExchanger.getConvertedAmount(amount, source.getCurrency(), target.getCurrency()));	
		storeTransaction(TransactionType.TRANSFER, amount, target, source);
		return true;
	}
	
	public void depositTo(float amount, Account target) {
		target.increaseFundsBy(amount);
		storeTransaction(TransactionType.DEPOSIT, amount, target, null);
	}
	
	public boolean tryWithdrawFrom(float amount, Account target) {
		
		if (!target.tryDecreaseFundsBy(amount))
		{
			return false;
		}
		
		storeTransaction(TransactionType.WITHDRAW, amount, target, null);
		return true;
	}
	
	private void storeTransaction(TransactionType transactionType, float amount, Account target, Account source) {
		// TODO: store transaction in database
		
		if (source != null)
		{
			System.out.printf("%s %.2f %s from %s to %s%n", transactionType, amount, source.getCurrency(), source.getID(), target.getID());
		}
		else
		{
			System.out.printf("%s %.2f %s to %s%n", transactionType, amount, target.getCurrency(), target.getID());
		}
	}
	
	private void getTransactionHistory() {
		// TODO: return transaction history from database
	}
}
