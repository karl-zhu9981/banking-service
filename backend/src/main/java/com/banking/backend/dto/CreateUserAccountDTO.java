package com.banking.backend.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CreateUserAccountDTO {
    @NotBlank(message = "User name is required")
    private String name;

    @Min(value = 0, message = "Initial balance of account must be non-negative")
    private Double initialBalance;

    public CreateUserAccountDTO() {}

    public CreateUserAccountDTO(String name, Double initialBalance) {
        this.name = name;
        this.initialBalance = initialBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }
}
