package org.example.ibank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.ibank.model.Account;
import org.example.ibank.model.AccountType;
import org.example.ibank.model.Currency;
import org.example.ibank.model.Customer;

import java.io.IOException;

public class IBankLauncher extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        //TODO showLoginScreen();

        //Uncomment the following to test profile-view:
        Account[] accounts = new Account[] {
                new Account("CHK1234", AccountType.Chequing, Currency.USD, 1500.00f),
                new Account("SAV5678", AccountType.Saving, Currency.CAD, 3000.00f)
        };
        var testCustomer = new Customer("1234", accounts);
        showProfileScreen(testCustomer);
    }

    public void showProfileScreen(Customer customer) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile-view.fxml"));
        Parent root = loader.load();

        ProfileController controller = loader.getController();
        controller.setAccounts(customer.accounts);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Main Profile");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}