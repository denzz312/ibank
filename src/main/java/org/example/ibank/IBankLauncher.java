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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class IBankLauncher extends Application {

    public static Stage primaryStage;
    private static Locale currentLocale = Locale.FRENCH;
    private static Consumer<FXMLLoader> currentScreenLoader;
    private static String currentFxmlPath;
    private final static double FIXED_WIDTH = 800;
    private final static double FIXED_HEIGHT = 600;

    public static ResourceBundle getBundle() 
    {
        return ResourceBundle.getBundle("org.example.ibank.i18n.Messages", currentLocale);
	}
    
    @Override
    public void start(Stage stage) throws IOException {
    	
    	SessionManager.StartDummySession();
        primaryStage = stage;
        primaryStage.setWidth(FIXED_WIDTH);
        primaryStage.setHeight(FIXED_HEIGHT);
        
        showLoginScreen();      
//        showAccountMainScreen();
//        showProfileScreen();
//        showPopUps();
    }
    
    public static void showScreen(String fxmlPath, Consumer<FXMLLoader> afterLoad) {
        try {
            currentFxmlPath = fxmlPath;
            currentScreenLoader = afterLoad;
            
            FXMLLoader loader = new FXMLLoader(IBankLauncher.class.getResource(fxmlPath), getBundle());
            Parent root = loader.load();

            if (afterLoad != null) {
                afterLoad.accept(loader);
            }

            Scene scene = new Scene(root, FIXED_WIDTH, FIXED_HEIGHT);
            primaryStage.centerOnScreen();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void showAccountMainScreen() throws IOException {    	
        showScreen("account-view.fxml", loader -> {
            AccountScreenController controller = loader.getController();
            controller.setAccount(SessionManager.currentAccount);
            primaryStage.setTitle(getBundle().getString("account.title"));
        });
    }

    public static void showProfileScreen() throws IOException {
        showScreen("profile-view.fxml", loader -> {
            ProfileController controller = loader.getController();
            controller.setAccounts(SessionManager.currentCustomer.accounts);
            primaryStage.setTitle(getBundle().getString("profile.title"));
        });
    }

    public static void showLoginScreen() throws IOException {
        showScreen("login-view.fxml", loader -> {
            primaryStage.setTitle(getBundle().getString("login.title"));
        });
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
    
    public static void toggleLanguage()
    {
    	if (currentLocale == Locale.FRENCH)
    	{
    		currentLocale = Locale.ENGLISH;
    	}
    	else 
    	{
        	currentLocale = Locale.FRENCH;
		}
    	
    	refreshCurrentScreen();
    }
    
    public static void refreshCurrentScreen() 
    {
        showScreen(currentFxmlPath, currentScreenLoader);		
	}
}
