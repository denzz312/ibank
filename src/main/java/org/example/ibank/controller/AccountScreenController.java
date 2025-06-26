package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

import org.example.ibank.model.Account;
import org.example.ibank.IBankLauncher;

public class AccountScreenController {

    @FXML
    private Label accountNumberLabel;

    @FXML
    private Label fundsLabel;
    
    private Account account;
    
    public void setAccount(Account account) {
        this.account = account;
        String last4 = account.getID().length() > 4
                ? account.getID().substring(account.getID().length() - 4)
                : account.getID();
        
        accountNumberLabel.setText("Account Number: ****" + last4);
        fundsLabel.setText("Funds: " + account.getFunds() + account.getCurrency().toString());
    }
    
    @FXML
    private void onWithdrawClicked() {
        try {
            FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/org/example/ibank/withdraw-view.fxml"));
            Parent root = loader.load();
            WithdrawController wc = loader.getController();
            wc.setAccount(account);                   // pass the current account

            Scene scene = new Scene(root, IBankLauncher.primaryStage.getWidth(), IBankLauncher.primaryStage.getHeight());
        	IBankLauncher.primaryStage.setTitle("Withdraw");
        	IBankLauncher.primaryStage.setScene(scene);
        	IBankLauncher.primaryStage.show();
        
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onDepositClicked() {
        try {
            FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/org/example/ibank/deposit-view.fxml"));
            Parent root = loader.load();

            DepositController controller = loader.getController();
            controller.setAccount(account);
            
            Scene scene = new Scene(root, IBankLauncher.primaryStage.getWidth(), IBankLauncher.primaryStage.getHeight());
            IBankLauncher.primaryStage.setTitle("Deposit");
            IBankLauncher.primaryStage.setScene(scene);
            IBankLauncher.primaryStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void onTransferClicked() {
        System.out.println("Transfer clicked");
        // TODO: open transfer screen
    }

    @FXML
    private void onTransactionHistoryClicked() {
        System.out.println("Transaction History clicked");
        // TODO: open transaction history screen
    }
}
