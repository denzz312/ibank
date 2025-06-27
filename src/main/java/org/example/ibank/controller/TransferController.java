package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import org.example.ibank.IBankLauncher;
import org.example.ibank.model.Account;
import org.example.ibank.model.AccountsDatabase;
import org.example.ibank.model.SessionManager;

public class TransferController {

    @FXML private TextField targetAccountField;
    @FXML private TextField amountField;
    @FXML private Label statusLabel;

    private Account currentAccount;

    public void setAccount(Account account) {
        this.currentAccount = account;
    }

    @FXML
    private void onTransferClicked() throws IOException {
        String targetID = targetAccountField.getText().trim();
        String amountText = amountField.getText().trim();

        if (targetID.isEmpty() || amountText.isEmpty()) {
            statusLabel.setText("Please enter both fields.");
            return;
        }

        float amount;
        
        try {
            amount = Float.parseFloat(amountText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid amount.");
            return;
        }

        if (amount <= 0) {
            statusLabel.setText("Amount must be greater than zero.");
            return;
        }
        
        Account targetAccount = AccountsDatabase.queryAccount(targetID);

        if (targetAccount == null)
        {
            statusLabel.setText("Target Account is invalid.");
            return;
        }
        
        if (!SessionManager.currentCustomer.tryTransferFunds(amount, currentAccount, targetAccount, true))
        {
            statusLabel.setText("Insufficient Funds.");
            return;
        }
        
        IBankLauncher.showAccountMainScreen();
    }
}
