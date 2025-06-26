package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmationPopUpController {
    @FXML private Label messageLabel;
    private boolean confirmed = false;

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    @FXML
    private void onYesClicked() {
        confirmed = true;
        ((Stage) messageLabel.getScene().getWindow()).close();
    }

    @FXML
    private void onNoClicked() {
        confirmed = false;
        ((Stage) messageLabel.getScene().getWindow()).close();
    }
}
