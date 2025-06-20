package org.example.ibank.model;

public class SessionManager {
	
	private static Customer currentCustomer;
	
	public static Customer getCurrentCustomer() {
		return currentCustomer;
	}
	
	public static void startSession(Customer customer) {
		currentCustomer = customer;
	}
	
	public static void ExitSession() {
		currentCustomer = null;
	}
	
}
