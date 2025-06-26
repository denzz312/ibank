package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.ibank.model.Account;

import java.io.IOException;

import org.example.ibank.IBankLauncher;

public class DepositController {

    @FXML 
    private TextField amountField;
    @FXML 
    private Label statusLabel;

    private Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    @FXML
    private void onDepositClicked() throws IOException {
        try {
            float amount = Float.parseFloat(amountField.getText().trim());
            if (amount <= 0) {
                statusLabel.setText("Amount must be greater than 0.");
                return;
            }
            if (amount%5 != 0)
            {
                statusLabel.setText("Amount must be of denominations $5, $10, $20, $50 or $100");
                return;
            }
            account.increaseFundsBy(amount);
            statusLabel.setText("Deposited $" + amount);
            //TODO: maybe add a confirmation screen
            IBankLauncher.showAccountMainScreen();

        } catch (NumberFormatException e) {
            statusLabel.setText("Enter a valid number.");
        }
    }
}
