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

    public static Stage primaryStage;
    private final float initialWidth = 400f;
    private final float initialHeight = 300f;

    @Override
    public void start(Stage stage) throws IOException {
    	
    	SessionManager.StartDummySession();
        primaryStage = stage;
        primaryStage.setWidth(initialWidth);
        primaryStage.setHeight(initialHeight);
        
        showLoginScreen();      
//        showAccountMainScreen();
//        showProfileScreen();
//        showPopUps();
    }
    
    public static void showAccountMainScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(IBankLauncher.class.getResource("account-view.fxml"));
        Parent root = loader.load();

        AccountScreenController controller = loader.getController();
        controller.setAccount(SessionManager.currentAccount);

        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
        primaryStage.setTitle("Bank Account");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showProfileScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(IBankLauncher.class.getResource("profile-view.fxml"));
        Parent root = loader.load();

        ProfileController controller = loader.getController();
        controller.setAccounts(SessionManager.currentCustomer.accounts);

        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
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
    
    public static void showLoginScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(IBankLauncher.class.getResource("login-view.fxml"));
        Parent root = loader.load();
                
        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();       
    }
    
    public static void main(String[] args) {
        launch();
    }
}
