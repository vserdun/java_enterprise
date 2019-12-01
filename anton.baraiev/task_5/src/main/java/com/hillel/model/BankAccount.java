package com.hillel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccount {
    private long id;
    private long amount;
    private User user;
}