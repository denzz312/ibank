package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ResourceBundle;

import org.example.ibank.IBankLauncher;
import org.example.ibank.model.Account;
import org.example.ibank.model.SessionManager;

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
        ResourceBundle bundle = IBankLauncher.getBundle();

        for (Account account : accounts) {
            String type = bundle.getString("profile." + account.getAccountType().name());
            String last4 = account.getID().length() > 4
                    ? account.getID().substring(account.getID().length() - 4)
                    : account.getID();
            float balance = account.getFunds();
            String currency = account.getCurrency().toString();

            String line = String.format("%s ****%s — %.2f %s", type, last4, balance, currency);
            Button button = new Button(line);
            button.setStyle("-fx-font-size: 14px; -fx-alignment: CENTER_LEFT;");
            button.setOnAction(e -> {
            	
            	try {
            		SessionManager.currentAccount = account;
					IBankLauncher.showAccountMainScreen();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            });

            accountList.getChildren().add(button);
        }
    }
}
