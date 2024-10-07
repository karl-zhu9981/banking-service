package com.banking.backend.model;


// User account object
public class UserAccount {
    private Long accountID;
    private String name;
    private Double balance;

    public UserAccount() {}

    public UserAccount(Long accountID, String name, Double balance) {
        this.accountID = accountID;
        this.name = name;
        this.balance = balance;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
