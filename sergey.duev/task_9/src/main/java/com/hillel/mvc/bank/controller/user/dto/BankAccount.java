package com.hillel.mvc.bank.controller.user.dto;

import lombok.Data;

@Data
public class BankAccount {

    private long id;

    private long createdAt;

    private double balance;
}
