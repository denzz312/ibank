package org.example.ibank.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.ibank.controller.ConfirmationPopUpController;
import org.example.ibank.controller.ErrorPopUpController;
import org.example.ibank.controller.SuccessPopUpController;

public class PopUpUtils {
    public static void showSuccessPopUp(String message) throws Exception {
        FXMLLoader loader = new FXMLLoader(PopUpUtils.class.getResource("/org/example/ibank/popups/success-popup.fxml"));
        Parent root = loader.load();
        SuccessPopUpController controller = loader.getController();
        controller.setMessage(message);

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Success");
        dialog.setScene(new Scene(root));
        dialog.showAndWait();
    }

    public static void showErrorPopUp(String message) throws Exception {
        FXMLLoader loader = new FXMLLoader(PopUpUtils.class.getResource("/org/example/ibank/popups/error-popup.fxml"));
        Parent root = loader.load();
        ErrorPopUpController controller = loader.getController();
        controller.setMessage(message);

        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Error");
        dialog.setScene(new Scene(root));
        dialog.showAndWait();
    }

    public static boolean showConfirmationPopUp(String message) throws Exception {
        FXMLLoader loader = new FXMLLoader(PopUpUtils.class.getResource("/org/example/ibank/popups/confirmation-popup.fxml"));
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
