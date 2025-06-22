package org.example.ibank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DatabaseTest {

	public class CustomerPin {
	    public String cardNumber;
	    public String pin;

	    public CustomerPin(String cardNumber, String pin) {
	        this.cardNumber = cardNumber;
	        this.pin = pin;
	    }
	}
	
	/*
	@Test
	public void connectToDatabase() {

		String DB_PATH = getClass().getClassLoader().getResource("db/bankDatabase.db").getPath();
		String DB_URL = "jdbc:sqlite:" + DB_PATH;
	    String query = "SELECT * FROM CustomerPins WHERE Pin=1111";

	    ArrayList<CustomerPin> result = new ArrayList<>();
	    
	      try (Connection conn = DriverManager.getConnection(DB_URL);
	           Statement stmt = conn.createStatement();
	           ResultSet rs = stmt.executeQuery(query)) {

	         while (rs.next()) {
	             String cardNumber = rs.getString("CardNumber");
	             String pin = rs.getString("Pin");
	             result.add(new CustomerPin(cardNumber, pin));
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }

	      for (CustomerPin customerPin : result) 
	      {
	    	  System.out.printf("%s ::: %s %n", customerPin.cardNumber, customerPin.pin);
	      }		
	}
	*/
	
	/*
	@Test
	public void updatePin() {
		String DB_URL = "jdbc:sqlite:data/bankDatabase.db";

	    String newPin = "1234";
	    String cardNumber = "1111";
	    String query = "UPDATE CustomerPins SET Pin = ? WHERE CardNumber = ?";

	      try (Connection conn = DriverManager.getConnection(DB_URL);
		           PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, newPin);
	        stmt.setString(2, cardNumber);

	        int rows = stmt.executeUpdate();
	        if (rows > 0)
	            System.out.println("Updated " + cardNumber + " to new pin: " + newPin);
	        else
	            System.out.println("Card not found: " + cardNumber);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	 */
	
	@Test
	public void testTryDecreaseFunds() {
		
		Customer customer1 = AccountsDatabase.queryCustomer("1111");
		
		SessionManager.currentCustomer = customer1;
		SessionManager.currentAccount = customer1.accounts[0]; // has 2000
		
		SessionManager.currentCustomer.tryTransferFunds(2000f, SessionManager.currentAccount, AccountsDatabase.queryAccount("2222"));
		// 1111 should have 0
		// 2222 should have 11000
		
		SessionManager.currentCustomer.tryTransferFunds(1000f, SessionManager.currentAccount, AccountsDatabase.queryAccount("2222"));
		// 1111 should have 0
		// 2222 should have 11000

		SessionManager.currentCustomer.depositTo(4000f, SessionManager.currentAccount);
		// 1111 should have 4000
		
		SessionManager.currentCustomer.tryWithdrawFrom(5000, SessionManager.currentAccount);
		// 1111 should have 4000
	}
	
}
