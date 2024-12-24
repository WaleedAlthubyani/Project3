package com.example.bankmanagementsystem.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDTO {

    @NotEmpty(message = "Please enter an account number")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$",message = "account number must follow the format XXXX-XXXX-XXXX-XXXX")
    private String accountNumber;

    @NotNull(message = "Please enter a balance")
    @PositiveOrZero(message = "Balance can't be a negative number")
    private Double balance;

}
