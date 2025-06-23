package org.example.ibank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.example.ibank.model.Account;

public class ProfileController {

    @FXML
    private VBox accountList;

    public void setAccounts(Account[] accounts) {
        accountList.getChildren().clear();

        if (accounts == null || accounts.length == 0) {
            Label noAccountsLabel = new Label("You have no accounts.");
            noAccountsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: grey;");
            accountList.getChildren().add(noAccountsLabel);
            return;
        }

        for (Account account : accounts) {
            String type = account.getAccountType().name();
            String last4 = account.getID().length() > 4
                    ? account.getID().substring(account.getID().length() - 4)
                    : account.getID();
            float balance = account.getFunds();
            String currency = account.getCurrency().toString();

            String line = String.format("%s ****%s — %.2f %s", type, last4, balance, currency);
            Button button = new Button(line);
            button.setStyle("-fx-font-size: 14px; -fx-alignment: CENTER_LEFT;");
            button.setOnAction(e -> {
                // TODO: Navigate to account screen
                System.out.println("Clicked: " + account.getID());
            });

            accountList.getChildren().add(button);
        }
    }
}
