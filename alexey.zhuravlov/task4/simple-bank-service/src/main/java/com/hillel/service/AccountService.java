package com.hillel.service;

import com.hillel.model.Account;

import java.util.List;

public interface AccountService {

    boolean withdraw(Account account, double amount);

    boolean deposit(Account account, double amount);

    boolean transfer(Account account1, Account account2, double amount);

    double getAccountAmount(Account account);

    List<String> getAccStatement(Account account);
}
