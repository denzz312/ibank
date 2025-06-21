package org.example.ibank.model;

public class SessionManager {
	
	public static Customer currentCustomer;
	public static Account currentAccount;
		
	public static void ExitSession() {
		currentCustomer = null;
		currentAccount = null;
	}
	
}
