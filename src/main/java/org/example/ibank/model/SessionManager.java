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
		Account account1 = new Account("12345678", AccountType.Chequing, Currency.CAD, 5000);
		Account account2 = new Account("87654321", AccountType.Chequing, Currency.USD, 10000);
		currentCustomer = new Customer("Dummy", new Account[] {account1, account2});
		currentAccount = account1;
	}
	
}
