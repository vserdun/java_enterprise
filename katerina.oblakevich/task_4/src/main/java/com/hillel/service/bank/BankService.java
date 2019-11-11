package com.hillel.service.bank;

import com.hillel.model.BankAccount;

public interface BankService {
    long getAmount(BankAccount bankAccount);

    String getAccountStatement(BankAccount bankAccount);

    boolean withdrawMoney(int amount, BankAccount bankAccount);

    boolean replenishAccount(int amount, BankAccount bankAccount);

    boolean transferMoney(int amount, BankAccount accountFrom, BankAccount accountTo);
}