package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.ibank.model.Account;

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
        System.out.println("Withdraw clicked");
        // TODO: open withdraw screen
    }

    @FXML
    private void onDepositClicked() {
        System.out.println("Deposit clicked");
        // TODO: open deposit screen
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
