package com.hillel.service;

import com.hillel.model.Account;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
public class TestAccountService implements AccountService {
    @Override
    public boolean withdraw(Account account, double amount) {
        log.info("Can't process withdraw, you are in the test mode now");
        return false;
    }

    @Override
    public boolean deposit(Account account, double amount) {
        log.info("Can't process deposit, you are in the test mode now");
        return false;
    }

    @Override
    public boolean transfer(Account account1, Account account2, double amount) {
        log.info("Can't process transfer, you are in the test mode now");
        return false;
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
