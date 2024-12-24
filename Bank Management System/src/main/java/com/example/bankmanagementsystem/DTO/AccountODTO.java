package com.example.bankmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountODTO {
    private String accountNumber;
    private Double balance;
    private Boolean isActive;
}
