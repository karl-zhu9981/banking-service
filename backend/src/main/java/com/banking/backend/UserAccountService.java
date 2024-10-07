package com.banking.backend.service;

import com.banking.backend.dto.TransferFundsDTO;
import com.banking.backend.model.UserAccount;
import com.banking.backend.repository.UserAccountRepository;
import com.banking.backend.transaction.Transaction;
import com.banking.backend.exception.AccountNotFoundException;
import com.banking.backend.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository repository;

    private final List<Transaction> transactions = new ArrayList<>();
    private long transactionIDCounter = 1;


    public UserAccount createUserAccount(String name, Double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance must be positive.");
        }
        UserAccount account = new UserAccount(null, name, initialBalance);
        return repository.save(account);
    }

    public void transferFunds(TransferFundsDTO dto) {
        UserAccount fromAccount = repository.findByID(dto.getFromAccountID());
        UserAccount toAccount = repository.findByID(dto.getToAccountID());

        Long fromAccountID = dto.getFromAccountID();
        Long toAccountID = dto.getToAccountID();
        Double amount = dto.getAmount();

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Invalid account ID(s)");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }
        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        // Update balances
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        // Create a transaction with the current balance of the fromAccount
        Transaction transaction = new Transaction(transactionIDCounter++, fromAccountID, toAccountID, amount, LocalDateTime.now(), fromAccount.getBalance());
        transactions.add(transaction);
    }

    public UserAccount getAccountByID(Long accountID) {
        return repository.findByID(accountID);
    }

    public List<Transaction> getTransactionHistory(Long accountID) {
        List<Transaction> accountTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getFromAccountID().equals(accountID) || transaction.getToAccountID().equals(accountID)) {
                accountTransactions.add(transaction);
            }
        }
        return accountTransactions;
    }
}
