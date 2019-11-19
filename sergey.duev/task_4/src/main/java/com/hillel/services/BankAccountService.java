package com.hillel.services;

import com.hillel.models.BankAccount;
import org.springframework.stereotype.Service;

@Service
public interface BankAccountService {

    void withdrawMoney(BankAccount bankAccount, double amount);

    void transferMoney(BankAccount bankAccountFrom, BankAccount bankAccountTo, double amount);

    void putMoney(BankAccount bankAccount, double amount);

    double getBalance(BankAccount bankAccount);

    void printStatement(BankAccount bankAccount);
}
