package com.hillel.mvc.bank.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class BankAccount {

    private long id;

    private long createdAt;

    private double balance;

    private List<Card> cards;
}
