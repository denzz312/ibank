package org.example.ibank.model;

public class Customer {

	public Account[] accounts;
	public final String cardNumber;

	public Customer(String cardNumber, Account[] accounts)
	{
		this.cardNumber = cardNumber;
		this.accounts = accounts;
	}

	public boolean tryTransferFunds (float amount, Account source, Account target) {
		return tryTransferFunds(amount, source, target, true);
	}

	public boolean tryTransferFunds (float amount, Account source, Account target, boolean storeTransactionInDatabase) {

		if (!source.tryDecreaseFundsBy(amount))
		{
			return false;
		}

		target.increaseFundsBy(CurrencyExchanger.getConvertedAmount(amount, source.getCurrency(), target.getCurrency()));	
		saveTransaction(TransactionType.TRANSFER, amount, target, source, storeTransactionInDatabase);
		return true;
	}

	public void depositTo(float amount, Account target) {
		depositTo(amount, target, true);
	}

	public void depositTo(float amount, Account target, boolean storeTransactionInDatabase) {
		target.increaseFundsBy(amount);
		saveTransaction(TransactionType.DEPOSIT, amount, target, null, storeTransactionInDatabase);
	}

	public boolean tryWithdrawFrom(float amount, Account target) {	
		return tryWithdrawFrom(amount, target, true);
	}

	public boolean tryWithdrawFrom(float amount, Account target, boolean storeTransactionInDatabase) {

		if (!target.tryDecreaseFundsBy(amount))
		{
			return false;
		}

		saveTransaction(TransactionType.WITHDRAW, amount, target, null, storeTransactionInDatabase);
		return true;
	}

	private void saveTransaction(TransactionType transactionType, float amount, Account target, Account source, boolean storeTransactionInDatabase) {

		if (source != null)
		{
			System.out.printf("%s %.2f %s from %s to %s%n", transactionType, amount, source.getCurrency(), source.getID(), target.getID());
		}
		else
		{
			System.out.printf("%s %.2f %s to %s%n", transactionType, amount, target.getCurrency(), target.getID());
		}

		if (!storeTransactionInDatabase)
		{
			return;
		}

		AccountsDatabase.storeTransaction(transactionType, amount, target, source);
		AccountsDatabase.updateAccount(target);

		if (source != null)
		{
			AccountsDatabase.updateAccount(source);
		}
		
		updateAccounts();
	}

	private void updateAccounts() 
	{
		accounts = AccountsDatabase.queryCustomer(cardNumber).accounts;
	}
	
	private void getTransactionHistory() {
		// TODO: return transaction history from database
	}
}
