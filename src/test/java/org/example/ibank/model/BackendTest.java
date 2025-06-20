package org.example.ibank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BackendTest {

	@Test
	public void testTryDecreaseFunds() {
		Account account1 = new Account("1", AccountType.Chequing, Currency.CAD, 5000);
		Account account2 = new Account("2", AccountType.Chequing, Currency.CAD, 10000);
		
		Customer customer1 = new Customer("1", new Account[] {account1});
		Customer customer2 = new Customer("2", new Account[] {account2});
		
		SessionManager.currentCustomer = customer1;
		SessionManager.currentAccount = customer1.accounts[0];
		
		SessionManager.currentCustomer.tryTransferFunds(3000f, SessionManager.currentAccount, account2);
		assertEquals(2000f, account1.getFunds(), 0.0001);
		assertEquals(13000f, account2.getFunds(), 0.0001);
		
		SessionManager.currentCustomer.depositTo(1000, SessionManager.currentAccount);
		assertEquals(3000f, account1.getFunds(), 0.0001);
		
		SessionManager.currentCustomer.tryWithdrawFrom(5000, SessionManager.currentAccount);
		assertEquals(3000f, account1.getFunds(), 0.0001);

	}
	
}
