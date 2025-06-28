package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import org.example.ibank.IBankLauncher;
import org.example.ibank.model.Account;
import org.example.ibank.model.AccountsDatabase;
import org.example.ibank.model.SessionManager;
import org.example.ibank.utils.PopUpUtils;

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
			PopUpUtils.showErrorPopUp(IBankLauncher.getBundle().getString("error.missingTransferInput"));
            return;
        }

        float amount;
        
        try {
            amount = Float.parseFloat(amountText);
        } catch (NumberFormatException e) {
			PopUpUtils.showErrorPopUp(IBankLauncher.getBundle().getString("error.invalidNumber"));
            return;
        }

        if (amount <= 0) {
			PopUpUtils.showErrorPopUp(IBankLauncher.getBundle().getString("error.negativeNumber"));
            return;
        }
        
        Account targetAccount = AccountsDatabase.queryAccount(targetID);

        if (targetAccount == null)
        {
			PopUpUtils.showErrorPopUp(IBankLauncher.getBundle().getString("error.invalidTargetAccount"));
            return;
        }
        
        if (currentAccount.getID().equals(targetAccount.getID()))
        {
			PopUpUtils.showErrorPopUp(IBankLauncher.getBundle().getString("error.transferSameAccount"));
            return;
        }
        
        if (!SessionManager.currentCustomer.tryTransferFunds(amount, currentAccount, targetAccount, true))
        {
			PopUpUtils.showErrorPopUp(IBankLauncher.getBundle().getString("error.insufficientFunds"));
            return;
        }
        
        IBankLauncher.showAccountMainScreen();
    }
}
