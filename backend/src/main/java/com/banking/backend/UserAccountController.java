package com.banking.backend.controller;

import com.banking.backend.dto.CreateUserAccountDTO;
import com.banking.backend.dto.TransferFundsDTO;
import com.banking.backend.model.UserAccount;
import com.banking.backend.service.UserAccountService;
import com.banking.backend.exception.AccountNotFoundException;
import com.banking.backend.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;
import java.util.Map;


import java.io.*;

@RestController
@RequestMapping("/api")
public class UserAccountController {

    @Autowired
    private UserAccountService service;

    @PostMapping("/createaccount")
    public ResponseEntity<?> createAccount(@Valid @RequestBody CreateUserAccountDTO dto) {

        // Validate input: check that the initial balance is positive
        if (dto.getInitialBalance() < 0) {
            return ResponseEntity.badRequest().body("Initial balance must be a positive number.");
        }

        try {
            // Call service layer to create the account
            UserAccount createdAccount = service.createUserAccount(dto.getName(), dto.getInitialBalance());
            return ResponseEntity.ok(createdAccount);
        } catch (Exception e) {
            // In case of any unhandled exceptions, return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create account: " + e.getMessage());
        }
    }

    @PostMapping("/transferfunds")
    public ResponseEntity<?> transferFunds(@Valid @RequestBody TransferFundsDTO dto) {
        // Validate input: ensure amount is positive
        if (dto.getAmount() <= 0) {
            return ResponseEntity.badRequest().body("Transfer amount must be positive.");
        }
        try {
            // Call service layer to perform the transfer
            service.transferFunds(dto);
            return ResponseEntity.ok().body(Map.of("message", "Transfer successful"));
        } catch (InvalidOperationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Failed to complete the transfer: " + e.getMessage()));
        }
    }


    @GetMapping("/{accountID}/history")
    public ResponseEntity<?> getAccountHistory(@PathVariable Long accountID) {
        try {
            // Call service layer to retrieve the account history
            return ResponseEntity.ok(service.getTransactionHistory(accountID));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve account history: " + e.getMessage());
        }
    }
}
