package com.hillel.mvc.bank.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class Card {

    private long id;

    private double amount;

    private String currency;

    private List<BankAccountInfo> bankAccounts;
}

