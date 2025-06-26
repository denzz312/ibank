package org.example.ibank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.ibank.controller.AccountScreenController;
import org.example.ibank.controller.ProfileController;
import org.example.ibank.model.SessionManager;
import org.example.ibank.utils.PopUpUtils;

import java.io.IOException;

public class IBankLauncher extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
    	
    	SessionManager.StartDummySession();
        this.primaryStage = stage;
        
        //TODO showLoginScreen();      
//        showAccountMainScreen();
//        showProfileScreen();
        showPopUps();
    }
    
    public void showAccountMainScreen() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("account-view.fxml"));
    	Parent root = loader.load();

    	AccountScreenController controller = loader.getController();
        controller.setAccount(SessionManager.currentAccount);
    	
    	Stage stage = new Stage();
    	stage.setTitle("Bank Account");
    	stage.setScene(new Scene(root));
    	stage.show();

    }

    public void showProfileScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile-view.fxml"));
        Parent root = loader.load();

        ProfileController controller = loader.getController();
        controller.setAccounts(SessionManager.currentCustomer.accounts);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Main Profile");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showPopUps() {
        try {
            PopUpUtils.showSuccessPopUp("Operation completed successfully!");
            PopUpUtils.showErrorPopUp("An error occurred.");
            boolean confirmed = PopUpUtils.showConfirmationPopUp("Are you sure?");
            if (confirmed) {
                PopUpUtils.showSuccessPopUp("Confirmed!");
            } else {
                PopUpUtils.showErrorPopUp("Cancelled!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch();
    }
}
