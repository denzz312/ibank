package org.example.ibank.model;

public class SessionManager {
	
	public static Customer currentCustomer;
	public static Account currentAccount;
		
	public static void ExitSession() {
		currentCustomer = null;
		currentAccount = null;
	}
	
	public static void StartDummySession()
	{
		Account account1 = new Account("Dummy 1", AccountType.Chequing, Currency.CAD, 10000);
		Account account2 = new Account("Dummy 2", AccountType.Chequing, Currency.USD, 10000);
		currentCustomer = new Customer("Dummy", new Account[] {account1, account2});
		currentAccount = account1;
	}
	
}
