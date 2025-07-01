package org.example.ibank.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import org.example.ibank.IBankLauncher;
import org.example.ibank.controller.ConfirmationPopUpController;
import org.example.ibank.controller.ErrorPopUpController;
import org.example.ibank.controller.SuccessPopUpController;

public class PopUpUtils {

    private static ResourceBundle bundle = IBankLauncher.getBundle();


    public static void showSuccessPopUp(String message, ResourceBundle bundle) throws IOException {
        FXMLLoader loader = new FXMLLoader(PopUpUtils.class.getResource("/org/example/ibank/popups/success-popup.fxml"), bundle);
        Parent root = loader.load();
        SuccessPopUpController controller = loader.getController();
        controller.setMessage(message);

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(PopUpUtils.bundle.getString("action.success"));
        dialog.setScene(new Scene(root));
        dialog.showAndWait();
    }

    public static void showErrorPopUp(String message, ResourceBundle bundle) throws IOException {
        FXMLLoader loader = new FXMLLoader(PopUpUtils.class.getResource("/org/example/ibank/popups/error-popup.fxml"), bundle);
        Parent root = loader.load();
        ErrorPopUpController controller = loader.getController();
        controller.setMessage(message);

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(PopUpUtils.bundle.getString("action.error"));
        dialog.setScene(new Scene(root));
        dialog.showAndWait();
    }

    public static boolean showConfirmationPopUp(String message, ResourceBundle bundle) throws IOException {
        FXMLLoader loader = new FXMLLoader(PopUpUtils.class.getResource("/org/example/ibank/popups/confirmation-popup.fxml"), bundle);
        Parent root = loader.load();
        ConfirmationPopUpController controller = loader.getController();
        controller.setMessage(message);

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Confirmation");
        dialog.setScene(new Scene(root));
        dialog.showAndWait();
        return controller.isConfirmed();
    }
}
