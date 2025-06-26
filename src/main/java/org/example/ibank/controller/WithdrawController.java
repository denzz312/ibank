package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;

import org.example.ibank.model.Account;
import org.example.ibank.IBankLauncher;

public class WithdrawController {

    @FXML private HBox customPane;
    @FXML private TextField customAmountField;
    @FXML private Label statusLabel;

    // business data
    private Account account;

    /*---- public hook ----*/
    public void setAccount(Account account) {
        this.account = account;
    }

    /*---- preset buttons ----*/
    @FXML private void withdraw100() throws IOException  { withdraw( 100f); }
    @FXML private void withdraw200() throws IOException  { withdraw( 200f); }
    @FXML private void withdraw500() throws IOException  { withdraw(500f); }
    @FXML private void withdraw1000() throws IOException { withdraw(1000f); }
    @FXML private void withdraw2000() throws IOException { withdraw(2000f); }

    /*---- custom flow ----*/
    @FXML private void showCustom() {
        customPane.setVisible(true);
        customPane.setManaged(true);
        customAmountField.requestFocus();
    }

    @FXML private void withdrawCustom() throws IOException {
        try 
        {
            float amount = Float.parseFloat(customAmountField.getText().trim());
            withdraw(amount);
        } 
        catch (NumberFormatException e) 
        {
            statusLabel.setText("Enter a valid number.");
        }
    }

    private void withdraw(float amount) throws IOException {
        if (amount <= 0) 
        {
            statusLabel.setText("Amount must be positive.");
            return;
        }
        if (amount%5 != 0)
        {
            statusLabel.setText("Amount must be of denominations $5, $10, $20, $50 or $100");
            return;
        }
        if (account.tryDecreaseFundsBy(amount)) 
        {
            statusLabel.setText("Withdrawn $" + amount);
            //TODO: maybe use confirmation screen
            IBankLauncher.showAccountMainScreen();
        } 
        else 
        {
            statusLabel.setText("Insufficient funds.");
        }
    }
}
