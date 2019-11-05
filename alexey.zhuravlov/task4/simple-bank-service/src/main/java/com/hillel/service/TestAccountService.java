package com.hillel.service;

import com.hillel.model.Account;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log
@Service
public class TestAccountService implements AccountService {
    @Override
    public void withdraw(Account account, double amount) {
        log.info("Can't process withdraw, you are in the test mode now");
    }

    @Override
    public void deposit(Account account, double amount) {
        log.info("Can't process deposit, you are in the test mode now");
    }

    @Override
    public void transfer(Account account1, Account account2, double amount) {
        log.info("Can't process transfer, you are in the test mode now");
    }

    @Override
    public double getAccountAmount(Account account) {
        double balance = account.getBalance();
        log.info("Account id:" + account.getId() + "balance is:" + balance);
        return balance;
    }

    @Override
    public List<String> getAccStatement(Account account) {
        List<String> accStatement = account.getAccStatement();
        accStatement.forEach(s -> log.info(s));
        return accStatement;
    }
}
