package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.ResourceBundle;

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
        
        ResourceBundle bundle = IBankLauncher.getBundle();
        accountNumberLabel.setText(bundle.getString("account.number") + last4);
        fundsLabel.setText(bundle.getString("account.funds") + account.getFunds() + account.getCurrency().toString());
    }
    
    @FXML
    private void onWithdrawClicked() {
    	IBankLauncher.showScreen("withdraw-view.fxml", loader  -> {
            WithdrawController wc = loader.getController();
            wc.setAccount(account);                   // pass the current account
            IBankLauncher.primaryStage.setTitle(IBankLauncher.getBundle().getString("withdraw.title"));
    	});    	
    }

    @FXML
    private void onDepositClicked() {   	    	
    	IBankLauncher.showScreen("deposit-view.fxml", loader  -> {
            DepositController controller = loader.getController();
            controller.setAccount(account);
            IBankLauncher.primaryStage.setTitle(IBankLauncher.getBundle().getString("deposit.title"));
    	});    	
    }

    @FXML
    private void onTransferClicked() {
    	
    	IBankLauncher.showScreen("transfer-view.fxml", loader  -> {
            TransferController tc = loader.getController();
            tc.setAccount(account);
            IBankLauncher.primaryStage.setTitle(IBankLauncher.getBundle().getString("transfer.title"));
    	});    	
    }

    @FXML
    private void onTransactionHistoryClicked() {
        IBankLauncher.showScreen("transaction-history-view.fxml", loader -> {
            TransactionHistoryController thc = loader.getController();
            thc.setAccount(account);
            IBankLauncher.primaryStage.setTitle(IBankLauncher.getBundle().getString("transaction.history.title"));
        });
    }
}
