package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import org.example.ibank.IBankLauncher;
import org.example.ibank.model.AccountsDatabase;
import org.example.ibank.model.CustomerAuthenticator;
import org.example.ibank.model.SessionManager;
import org.example.ibank.utils.PopUpUtils;

public class LoginController {

    @FXML private TextField cardNumberField;
    @FXML private PasswordField pinField;
    @FXML private Label statusLabel;

    @FXML
    private void onLoginClicked() throws IOException {
        String card = cardNumberField.getText().trim();
        String pin  = pinField.getText().trim();

        if (card.isEmpty() || pin.isEmpty()) {
			PopUpUtils.showErrorPopUp(IBankLauncher.getBundle().getString("error.missingLoginInput"));
            return;
        }

        boolean ok = CustomerAuthenticator.checkLoginValidity(card, pin);
        if (ok) 
        {
        	SessionManager.currentCustomer = AccountsDatabase.queryCustomer(card);
            IBankLauncher.showProfileScreen();
        } 
        else 
        {
			PopUpUtils.showErrorPopUp(IBankLauncher.getBundle().getString("error.invalidLogin"));
        }
    }
}
