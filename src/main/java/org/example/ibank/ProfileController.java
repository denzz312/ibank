package org.example.ibank;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.example.ibank.model.Account;

import java.util.List;

public class ProfileController {

    @FXML
    private VBox accountList;

    public void setAccounts(Account[] accounts) {
        if(accounts == null) return;

        for (Account account : accounts) {
            String type = account.getAccountType().name();
            String last4 = account.getID().length() > 4
                    ? account.getID().substring(account.getID().length() - 4)
                    : account.getID();
            float balance = account.getFunds();
            String currency = account.getCurrency().toString();

            String line = String.format("%s ****%s — %.2f %s", type, last4, balance, currency);
            Label label = new Label(line);
            label.setStyle("-fx-font-size: 14px;");

            accountList.getChildren().add(label);
        }
    }
}
