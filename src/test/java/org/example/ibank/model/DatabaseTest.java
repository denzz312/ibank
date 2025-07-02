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

		// Withdraw 2500 (should succeed, under 3000 limit)
		customer1.accounts[0].setTransactions(null);
		boolean firstWithdraw = customer1.tryWithdrawFrom(500f, customer1.accounts[0], true);
		assertEquals(true, firstWithdraw);
		assertEquals(customer1.accounts[0].getFunds(), initialFunds + 2000f + 4000f - 500f);

		// Withdraw another 600 (should fail, would exceed 3000 limit)
		boolean secondWithdraw = customer1.tryWithdrawFrom(600f, customer1.accounts[0], true);
		assertEquals(false, secondWithdraw);
		assertEquals(customer1.accounts[0].getFunds(), initialFunds + 2000f + 4000f - 500f);
	}
	
}
