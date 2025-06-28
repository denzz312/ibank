package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.ibank.model.Account;
import org.example.ibank.utils.PopUpUtils;

import java.io.IOException;
import java.util.ResourceBundle;

import org.example.ibank.IBankLauncher;

public class DepositController {

    @FXML 
    private TextField amountField;
    @FXML 
    private Label statusLabel;

    private Account account;
    private ResourceBundle bundle;

    public void setAccount(Account account) {
        this.account = account;
        bundle = IBankLauncher.getBundle();
    }

    @FXML
    private void onDepositClicked() throws IOException {
        try {
            float amount = Float.parseFloat(amountField.getText().trim());
            if (amount <= 0) {
    			PopUpUtils.showErrorPopUp(bundle.getString("error.negativeNumber"));
                return;
            }
            if (amount%5 != 0)
            {
    			PopUpUtils.showErrorPopUp(bundle.getString("error.invalidDenomination"));
                return;
            }
            account.increaseFundsBy(amount);
            statusLabel.setText("Deposited $" + amount);
            //TODO: maybe add a confirmation screen
            IBankLauncher.showAccountMainScreen();

        } catch (NumberFormatException e) {
			PopUpUtils.showErrorPopUp(bundle.getString("error.invalidNumber"));
        }
    }
}
