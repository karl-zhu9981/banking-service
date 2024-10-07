package com.banking.backend.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TransferFundsDTO {

    @NotNull(message = "The sender account ID is required")
    private Long fromAccountID;

    @NotNull(message = "The receiver account ID is required")
    private Long toAccountID;

    @Min(value = 0, message = "Amount to be transferred must be positive")
    private Double amount;

    public TransferFundsDTO() {}

    public TransferFundsDTO(Long fromAccountID, Long toAccountID, Double amount) {
        this.fromAccountID = fromAccountID;
        this.toAccountID = toAccountID;
        this.amount = amount;
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
}
