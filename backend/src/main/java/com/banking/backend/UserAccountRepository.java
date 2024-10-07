package com.banking.backend.repository;

import com.banking.backend.model.UserAccount;
import com.banking.backend.exception.AccountNotFoundException;
import com.banking.backend.exception.InvalidOperationException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.io.*;


// A repository of all of the user accounts. 

@Repository
public class UserAccountRepository {
    private final Map<Long, UserAccount> accounts = new HashMap<>();
    private Long currentID = 1L;

    // Saving the user account in a hashmap.
    public UserAccount save(UserAccount account) {
        account.setAccountID(currentID++);
        accounts.put(account.getAccountID(), account);
        return account;
    }

    public UserAccount findByID(Long accountID) {
        UserAccount account = accounts.get(accountID);
        if (account == null) {
            // Throw custom exception if account is not found
            throw new AccountNotFoundException("Account not found with ID: " + accountID);
        }
        return account;
    }

    public Map<Long, UserAccount> findAll() {
        return accounts;
    }
}
