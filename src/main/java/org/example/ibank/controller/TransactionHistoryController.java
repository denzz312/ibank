package org.example.ibank.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.ibank.model.Account;
import org.example.ibank.model.Transaction;
import org.example.ibank.model.TransactionType;

import java.time.LocalDateTime;

public class TransactionHistoryController {

    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, String> sourceColumn;
    @FXML
    private TableColumn<Transaction, String> targetColumn;
    @FXML
    private TableColumn<Transaction, Float> amountColumn;
    @FXML
    private TableColumn<Transaction, LocalDateTime> dateColumn;
    @FXML
    private TableColumn<Transaction, TransactionType> typeColumn;

    private Account account;

    @FXML
    public void initialize() {
        sourceColumn.setCellValueFactory(new PropertyValueFactory<>("sourceAccountId"));
        targetColumn.setCellValueFactory(new PropertyValueFactory<>("targetAccountId"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    public void setAccount(Account account) {
        this.account = account;
        ObservableList<Transaction> transactions = FXCollections.observableArrayList(account.getTransactions());
        transactionTable.setItems(transactions);
    }
}
