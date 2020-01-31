package com.hillel.service;

import com.hillel.model.BankAccount;

public interface BankOperationService {
    void getAccountAmount(BankAccount bankAccount);

    void getAccountStatement(BankAccount bankAccount);

    boolean withdrawMoneyFromAccount(int amount, BankAccount bankAccount);

    boolean replenishAccount(int amount, BankAccount bankAccount);

    boolean transferMoneyToAnotherAccount(int amount, BankAccount accountFrom, BankAccount accountTo);
}
