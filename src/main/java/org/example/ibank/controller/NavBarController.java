package org.example.ibank.controller;

import javafx.fxml.FXML;

import java.io.IOException;

import org.example.ibank.IBankLauncher;

public class NavBarController {

    @FXML
    private void onHomeClicked() throws IOException {
        IBankLauncher.showAccountMainScreen();    
    }

    @FXML
    private void onToggleLanguage() {
        IBankLauncher.toggleLanguage();
    }

    @FXML
    private void onSignOutClicked() throws IOException {
        IBankLauncher.showLoginScreen();
    }
}
