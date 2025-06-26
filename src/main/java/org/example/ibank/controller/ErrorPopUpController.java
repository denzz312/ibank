package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorPopUpController {
    @FXML private Label messageLabel;

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    @FXML
    private void onOkClicked() {
        ((Stage) messageLabel.getScene().getWindow()).close();
    }
}
