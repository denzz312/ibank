package org.example.ibank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.ibank.model.SessionManager;

import java.io.IOException;

public class IBankLauncher extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
    	
    	SessionManager.StartDummySession();
        this.primaryStage = stage;
        
        //TODO showLoginScreen();      
        showAccountMainScreen();     
        //showProfileScreen();
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
    
    public static void main(String[] args) {
        launch();
    }
}
