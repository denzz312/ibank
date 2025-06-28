package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ResourceBundle;

import org.example.ibank.model.Account;
import org.example.ibank.IBankLauncher;

public class WithdrawController {

    @FXML private HBox customPane;
    @FXML private TextField customAmountField;
    @FXML private Label statusLabel;

    // business data
    private Account account;
    private ResourceBundle bundle;
    
    /*---- public hook ----*/
    public void setAccount(Account account) {
        this.account = account;
        bundle = ResourceBundle.getBundle("org.example.ibank.i18n.Messages", IBankLauncher.currentLocale);
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
            statusLabel.setText(bundle.getString("error.invalidNumber"));
        }
    }

    private void withdraw(float amount) throws IOException {
        if (amount <= 0) 
        {
            statusLabel.setText(bundle.getString("error.negativeNumber"));
            return;
        }
        if (amount%5 != 0)
        {
            statusLabel.setText(bundle.getString("error.invalidDenomination"));
            return;
        }
        if (account.tryDecreaseFundsBy(amount)) 
        {
            IBankLauncher.showAccountMainScreen();
        } 
        else 
        {
            statusLabel.setText(bundle.getString("error.insufficientFunds"));
        }
    }
}
