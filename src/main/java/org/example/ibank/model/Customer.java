package org.example.ibank.model;

public class Customer {

	public Account[] accounts;
	public final String cardNumber;
	
	public Customer(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public boolean tryTransferFunds (float amount, Account source, Account target) {
		
		if (!source.tryDecreaseFundsBy(amount))
		{
			return false;
		}
		
		target.increaseFundsBy(amount);
		return true;
	}
	
	public void depositTo(float amount, Account target) {
		target.increaseFundsBy(amount);
	}
	
	public boolean tryWithdrawFrom(float amount, Account target) {
		return target.tryDecreaseFundsBy(amount);
	}
	
	private void storeTransaction(TransactionType transactionType, float amount, Account target, Account source) {
		
		if (source != null)
		{
			System.out.printf("%s %.2f from %s to %s%n", transactionType, amount, source, target);
		}
		else
		{
			System.out.printf("%s %.2f to %s%n", transactionType, amount, target);
		}
	}
}
