package com.hillel.mvc.bank.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String address;
    BankAccount account;
}
