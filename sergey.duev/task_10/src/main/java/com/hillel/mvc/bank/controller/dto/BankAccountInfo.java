package com.hillel.mvc.bank.controller.dto;

import lombok.Data;

@Data
public class BankAccountInfo {

    private long id;

    private long createdAt;

    private double balance;
}
