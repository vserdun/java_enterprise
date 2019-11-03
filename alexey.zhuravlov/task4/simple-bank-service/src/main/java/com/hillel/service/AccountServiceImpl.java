package com.hillel.service;

import com.hillel.model.Account;
import lombok.extern.flogger.Flogger;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Log
@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public void withdraw(Account account, double amount) {
        account.setBalance(account.getBalance() - amount);
        String operation = LocalDate.now() + " withdraw from account id " + account.getId() + " amount " + amount;
        account.addAccStatement(operation);
        log.info(operation);
    }

    @Override
    public void deposit(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        String operation = LocalDate.now() + " deposit to account id " + account.getId() + " amount " + amount;
        account.addAccStatement(operation);
        log.info(operation);
    }

    @Override
    public void transfer(Account account1, Account account2, double amount) {
        withdraw(account1, amount);
        deposit(account2, amount);
        String operation = LocalDate.now() + " transfer from account id " + account1.getId() + " to account id " + account2.getId() + " amount " + amount;
        account1.addAccStatement(operation);
        account2.addAccStatement(operation);
        log.info(operation);
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
        accStatement.forEach(s -> log.info(s + "\n"));
        return accStatement;
    }
}
