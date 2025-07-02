package org.example.ibank.model;

import java.time.LocalDateTime;

public class Transaction {
    private final String sourceAccountId;
    private final String targetAccountId;
    private final float amount;
    private final LocalDateTime date;
    private final TransactionType type;

    public Transaction(String sourceAccountId, String targetAccountId, float amount, LocalDateTime date, TransactionType type) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    public float getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }
}