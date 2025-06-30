package org.example.ibank.model;

import java.sql.*;

public class CurrencyExchanger {

	private static final String DB_URL = "jdbc:sqlite:data/bankDatabase.db";

	public static float getExchangeRate(Currency source, Currency target) {
		String sql = "SELECT rate FROM exchange_rates WHERE source_currency = ? AND target_currency = ?";

		try (Connection conn = DriverManager.getConnection(DB_URL);
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, source.name());
			stmt.setString(2, target.name());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getFloat("rate");
			} else {
				throw new RuntimeException("Exchange rate not found: " + source + " to " + target);
			}

		} catch (SQLException e) {
			throw new RuntimeException("Error accessing SQLite DB", e);
		}
	}
	
	public static float getConvertedAmount(float amount, Currency source, Currency target) {	
		return amount * getExchangeRate(source, target);
	}
	
}
