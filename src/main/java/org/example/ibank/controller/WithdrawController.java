package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.scene.layout.VBox;
import org.example.ibank.model.Account;
import org.example.ibank.model.SessionManager;
import org.example.ibank.utils.PopUpUtils;
import org.example.ibank.IBankLauncher;

public class WithdrawController {

    @FXML private VBox customPane;
    @FXML private TextField customAmountField;
    @FXML private Label statusLabel;

    // business data
    private Account account;
    private ResourceBundle bundle;
    
    /*---- public hook ----*/
    public void setAccount(Account account) {
        this.account = account;
        bundle = IBankLauncher.getBundle();
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
			PopUpUtils.showErrorPopUp(bundle.getString("error.invalidNumber"), bundle);
        }
    }

    private void withdraw(float amount) throws IOException {
        if (amount <= 0) 
        {
			PopUpUtils.showErrorPopUp(bundle.getString("error.negativeNumber"), bundle);
            return;
        }
        if (amount%5 != 0)
        {
			PopUpUtils.showErrorPopUp(bundle.getString("error.invalidDenomination"), bundle);
            return;
        }

        boolean isConfirmed = PopUpUtils.showConfirmationPopUp("Withdraw $" + amount + " from account " + account.getID() + "?", bundle);

        if (!isConfirmed) return;


        if (SessionManager.currentCustomer.tryWithdrawFrom(amount, account)) 
        {
            IBankLauncher.showAccountMainScreen();
        } 
        else 
        {
			PopUpUtils.showErrorPopUp(bundle.getString("error.insufficientFunds"), bundle );
            return;
        }
    }
}
