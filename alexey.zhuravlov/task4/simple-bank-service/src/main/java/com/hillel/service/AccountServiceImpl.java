package com.hillel.service;

import com.hillel.model.Account;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Log
@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public boolean withdraw(Account account, double amount) {
        String operation;
        if (amount > account.getBalance()) {
            operation = LocalDate.now() + " not enough resources on accout " + account.getId();
            return false;
        } else {
            account.setBalance(account.getBalance() - amount);
            operation = LocalDate.now() + " withdraw from account id " + account.getId() + " amount " + amount;
        }

        account.addAccStatement(operation);
        log.info(operation);
        return true;
    }

    @Override
    public boolean deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        String operation = LocalDate.now() + " deposit to account id " + account.getId() + " amount " + amount;
        account.addAccStatement(operation);
        log.info(operation);
        return true;
    }

    @Override
    public boolean transfer(Account account1, Account account2, double amount) {
        String operation;
        if (amount > account1.getBalance()) {
            operation = LocalDate.now() + " not enough resources on accout " + account1.getId();
            return false;
        } else {
            account1.setBalance(account1.getBalance() - amount);
            account2.setBalance(account2.getBalance() + amount);
            operation = LocalDate.now() + " transfer from account id " + account1.getId() + " to account id " + account2.getId() + " amount " + amount;
        }
        account1.addAccStatement(operation);
        account2.addAccStatement(operation);
        log.info(operation);
        return true;
    }

    @Override
    public double getAccountAmount(Account account) {
        double balance = account.getBalance();
        log.info("_________Account balance_________");
        log.info("Account id:" + account.getId() + "balance is:" + balance);
        return balance;
    }

    @Override
    public List<String> getAccStatement(Account account) {
        List<String> accStatement = account.getAccStatement();
        log.info("_________Account Statement_________");
        accStatement.forEach(s -> log.info(s));
        return accStatement;
    }
}
