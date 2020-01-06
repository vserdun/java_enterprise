package com.hillel.bankserviceboot.service;

import com.hillel.bankserviceboot.model.AccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private AccountService accountService;

    @Transactional
    public boolean withdraw(int accountId, double amount) {
        String operation;
        AccountEntity account = accountService.getAccount(accountId);
        if (amount > account.getAccountBalance()) {
            operation = LocalDate.now() + " not enough resources on account " + account.getAccountId();
            return false;
        } else {
            account.setAccountBalance(account.getAccountBalance() - amount);
            operation = LocalDate.now() + " withdraw from account id " + account.getAccountId() + " amount " + amount;
        }

        account.addAccStatement(operation);
        log.info(operation);
        return true;
    }

    @Transactional
    @Override
    public boolean deposit(int accountId, double amount) {
        AccountEntity account = accountService.getAccount(accountId);
        account.setAccountBalance(account.getAccountBalance() + amount);
        String operation = LocalDate.now() + " deposit to account id " + account.getAccountId() + " amount " + amount;
        account.addAccStatement(operation);
        log.info(operation);
        return true;
    }

    @Override
    public boolean transfer(int accountId1, int accountId2, double amount) {
        AccountEntity account1 = accountService.getAccount(accountId1);
        AccountEntity account2 = accountService.getAccount(accountId2);
        String operation;
        if (amount > account1.getAccountBalance()) {
            operation = LocalDate.now() + " not enough resources on account " + account1.getAccountId();
            return false;
        } else {
            account1.setAccountBalance(account1.getAccountBalance() - amount);
            account2.setAccountBalance(account2.getAccountBalance() + amount);
            operation = LocalDate.now() + " transfer from account id " + account1.getAccountId() + " to account id " + account2.getAccountId() + " amount " + amount;
        }
        accountService.updateAccount(account1);
        accountService.updateAccount(account2);
        account1.addAccStatement(operation);
        account2.addAccStatement(operation);
        log.info(operation);
        return true;
    }

    @Override
    public double getAccountEntityAmount(int accountId) {
        AccountEntity account = accountService.getAccount(accountId);
        double balance = account.getAccountBalance();
        log.info("_________AccountEntity balance_________");
        log.info("AccountEntity id:" + account.getAccountId() + "balance is:" + balance);
        return balance;
    }

    @Override
    public String getAccStatement(int accountId) {
        AccountEntity account = accountService.getAccount(accountId);
        String accStatement = account.getAccStatement();
        log.info("_________AccountEntity Statement_________");
        log.info(accStatement);
        return accStatement;
    }

    @Override
    public boolean isTransferSupported() {
        return true;
    }
}
