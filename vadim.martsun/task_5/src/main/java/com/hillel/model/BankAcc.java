package com.hillel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAcc {
    private float balance;
    private User user;
}
