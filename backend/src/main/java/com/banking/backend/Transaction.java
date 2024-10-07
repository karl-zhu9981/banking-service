package com.banking.backend.transaction;

import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private Long fromAccountID;
    private Long toAccountID;
    private Double amount;
    private LocalDateTime timestamp;
    private Double currentBalance;

    public Transaction(Long id, Long fromAccountID, Long toAccountID, Double amount, LocalDateTime timestamp, Double currentBalance) {
        this.id = id;
        this.fromAccountID = fromAccountID;
        this.toAccountID = toAccountID;
        this.amount = amount;
        this.timestamp = timestamp;
        this.currentBalance = currentBalance;
    }

    // Getters and Setters
    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public Long getFromAccountID() {
        return fromAccountID;
    }

    public void setFromAccountID(Long fromAccountID) {
        this.fromAccountID = fromAccountID;
    }

    public Long getToAccountID() {
        return toAccountID;
    }

    public void setToAccountID(Long toAccountID) {
        this.toAccountID = toAccountID;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getCurrentBalance() {
        return currentBalance; 
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance; 
    }
}
