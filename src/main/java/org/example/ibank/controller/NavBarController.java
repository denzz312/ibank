package org.example.ibank.controller;

import javafx.fxml.FXML;

import java.io.IOException;

import org.example.ibank.IBankLauncher;
import org.example.ibank.utils.PopUpUtils;

public class NavBarController {

    @FXML
    private void onHomeClicked() throws IOException {
        IBankLauncher.showProfileScreen();    
    }

    @FXML
    private void onToggleLanguage() {
        IBankLauncher.toggleLanguage();
    }

    @FXML
    private void onSignOutClicked() throws IOException {
        boolean isConfirmed = PopUpUtils.showConfirmationPopUp(
            IBankLauncher.getBundle().getString("nav.signOutConfirm"), IBankLauncher.getBundle());
        if (!isConfirmed) {
            return;
        }

        IBankLauncher.showLoginScreen();
    }
}
