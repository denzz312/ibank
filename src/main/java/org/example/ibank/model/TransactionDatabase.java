package org.example.ibank.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionDatabase {

    private static final String DB_URL = "jdbc:sqlite:data/bankDatabase.db";


    public static List<Transaction> getTransactionsForAccount(String accountId) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String query = """
        SELECT TargetAccountID, Funds, Timestamp, TransactionType, SourceAccountID
        FROM Transactions
        WHERE SourceAccountID = ? OR TargetAccountID = ?
        ORDER BY Timestamp DESC
    """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, accountId);
            stmt.setString(2, accountId);

            ResultSet rs = stmt.executeQuery();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

            while (rs.next()) {
                String sourceId = rs.getString("SourceAccountID");
                String targetId = rs.getString("TargetAccountID");
                float amount = rs.getFloat("Funds");
                LocalDateTime date = LocalDateTime.parse(rs.getString("Timestamp"), formatter);
                TransactionType type = TransactionType.valueOf(rs.getString("TransactionType"));

                // Adjust constructor as per your Transaction class definition
                transactions.add(new Transaction(sourceId, targetId, amount, date, type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
