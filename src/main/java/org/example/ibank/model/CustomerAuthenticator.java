package org.example.ibank.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.ibank.utils.HashUtils;

public class CustomerAuthenticator {

	private static final String DB_URL = "jdbc:sqlite:data/bankDatabase.db";

	public static boolean checkLoginValidity(String cardNumber, String inputPin)
	{
		String query = "SELECT Pin FROM CustomerPins WHERE CardNumber=?";

		try (Connection conn = DriverManager.getConnection(DB_URL);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, cardNumber);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String storedHashedPin = rs.getString("Pin");
				String hashedInputPin = HashUtils.sha256(inputPin);
				return storedHashedPin.equals(hashedInputPin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

		}
	
}
