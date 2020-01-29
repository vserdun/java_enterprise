package com.hillel.mvc.bank.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private long id;

    private String name;

    private String lastName;

    private String age;

    private String email;

    private String gender;

    private List<BankAccount> bankAccounts;

    private Address address;
}
