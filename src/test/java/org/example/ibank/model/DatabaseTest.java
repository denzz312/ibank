package org.example.ibank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DatabaseTest {

	
	@Test
	public void testTryDecreaseFunds() {
		
		Customer customer1 = AccountsDatabase.queryCustomer("1111");
		float initialFunds = customer1.accounts[0].getFunds();
				
		SessionManager.StartDummySession();		
		
		SessionManager.currentCustomer.tryTransferFunds(2000f, SessionManager.currentAccount, customer1.accounts[0], true);	
		assertEquals(customer1.accounts[0].getFunds(), initialFunds + 2000f);
			
		SessionManager.currentCustomer.tryTransferFunds(9999999f, SessionManager.currentAccount, customer1.accounts[0], true);
		assertEquals(customer1.accounts[0].getFunds(), initialFunds + 2000f);


		customer1.depositTo(4000f, customer1.accounts[0], true);
		assertEquals(customer1.accounts[0].getFunds(), initialFunds + 2000f + 4000f);
		
		customer1.tryWithdrawFrom(6000f, customer1.accounts[0], true);
		assertEquals(customer1.accounts[0].getFunds(), initialFunds);

	}
	
}
