package org.example.ibank.model;

import java.sql.*;
import java.util.ArrayList;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class AccountsDatabase {

	private static final String DB_URL = "jdbc:sqlite:data/bankDatabase.db";

	public static Account queryAccount(String accountID) {
		String query = "SELECT * FROM Accounts WHERE AccountID=?";

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, accountID);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				AccountType accountType = null;

				try {
					accountType = Enum.valueOf(AccountType.class, rs.getString("AccountType"));
				} catch (IllegalArgumentException e) {
					System.err.println("Invalid AccountType in DB: " + rs.getString("AccountType"));
					return null;
				}

				Currency currency = null;

				try {
					currency = Enum.valueOf(Currency.class, rs.getString("Currency"));
				} catch (IllegalArgumentException e) {
					System.err.println("Invalid Currency in DB: " + rs.getString("Currency"));
					return null;
				}

				float funds = rs.getFloat("Funds");
				return new Account(accountID, accountType, currency, funds);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Customer queryCustomer(String cardNumber) {
		String query = """
				    SELECT a.AccountID, a.AccountType, a.Currency, a.Funds
				    FROM CustomersAccounts ca
				    JOIN Accounts a ON ca.AccountID = a.AccountID
				    WHERE ca.CardNumber = ?
				""";

		ArrayList<Account> accounts = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, cardNumber);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String accountId = rs.getString("AccountID");

				AccountType accountType = null;

				try {
					accountType = Enum.valueOf(AccountType.class, rs.getString("AccountType"));
				} catch (IllegalArgumentException e) {
					System.err.println("Invalid AccountType in DB: " + rs.getString("AccountType"));
					continue;
				}

				Currency currency = null;

				try {
					currency = Enum.valueOf(Currency.class, rs.getString("Currency"));
				} catch (IllegalArgumentException e) {
					System.err.println("Invalid Currency in DB: " + rs.getString("Currency"));
					continue;
				}

				float funds = rs.getFloat("Funds");
				accounts.add(new Account(accountId, accountType, currency, funds));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Customer(cardNumber, accounts.toArray(new Account[0]));
	}

	public static boolean updateAccount(Account account) {
		
		String query = "UPDATE Accounts SET AccountType = ?, Currency = ?, Funds = ? WHERE AccountID = ?";
		
		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement stmt = conn.prepareStatement(query)) {
	    	  
	    	  stmt.setString(1, account.getAccountType().name());
	    	  stmt.setString(2, account.getCurrency().name());
	    	  stmt.setFloat(3, account.getFunds());
	    	  stmt.setString(4, account.getID());
	    	  
	    	  int rows = stmt.executeUpdate();
	    	  
	    	  return rows > 0;
	      }
	      catch (SQLException e) {
	    	  e.printStackTrace();
	    	  return false;
	      }
	}
	
	public static boolean storeTransaction(TransactionType transactionType, float amount, Account target, Account source) {
		// TODO: store transaction in database
		
		Instant currentTimestamp = Instant.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
		        .withZone(ZoneId.systemDefault());
		String formattedTimestamp = formatter.format(currentTimestamp);
		
		String query = "INSERT INTO Transactions (Timestamp, TransactionType, SourceAccountID, TargetAccountID, Funds) VALUES (?, ?, ?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement stmt = conn.prepareStatement(query)) {
	    	  
	    	  stmt.setString(1, formattedTimestamp);
	    	  stmt.setString(2, transactionType.name());
	    	  if (source != null)
	    	  {
		    	  stmt.setString(3, source.getID());
	    	  }
	    	  else 
	    	  {
	    		  stmt.setNull(3, Types.VARCHAR);
	    	  }
	    	  stmt.setString(4, target.getID());
	    	  stmt.setFloat(5, amount); 
	    	  
	    	  int rows = stmt.executeUpdate();
	    	  
	    	  return rows > 0;
		}	
		catch (SQLException e) {
	    	  e.printStackTrace();
	    	  return false;
		}
	}
	
}
