package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.ibank.model.Account;
import org.example.ibank.model.SessionManager;
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
    			PopUpUtils.showErrorPopUp(bundle.getString("error.negativeNumber"), bundle);
                return;
            }
            if (amount%5 != 0)
            {
    			PopUpUtils.showErrorPopUp(bundle.getString("error.invalidDenomination"), bundle );
                return;
            }

            boolean isConfirmed = PopUpUtils.showConfirmationPopUp(
                bundle.getString("deposit.confirmationMessage") + " " + amount + " " + bundle.getString("deposit.toAccount") + " " + account.getID() + "?", bundle);

            if (!isConfirmed) return;

            SessionManager.currentCustomer.depositTo(amount, account);
            statusLabel.setText("Deposited $" + amount);
            IBankLauncher.showAccountMainScreen();

        } catch (NumberFormatException e) {
			PopUpUtils.showErrorPopUp(bundle.getString("error.invalidNumber"), bundle );
        }
    }
}
