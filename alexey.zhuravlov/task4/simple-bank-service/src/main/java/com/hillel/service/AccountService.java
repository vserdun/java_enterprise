package com.hillel.service;

import com.hillel.model.Account;

import java.util.List;

public interface AccountService {

    void withdraw (Account account, double amount);

    void deposit (Account account, double amount);

    void transfer (Account account1, Account account2, double amount);

    double getAccountAmount (Account account);

    List<String> getAccStatement(Account account);
}
