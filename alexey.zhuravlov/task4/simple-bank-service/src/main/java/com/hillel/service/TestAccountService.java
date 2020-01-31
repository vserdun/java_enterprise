package com.hillel.service;

import com.hillel.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TestAccountService extends AccountServiceImpl {
    @Override
    public double getAccountAmount(Account account) {
        return super.getAccountAmount(account);
    }

    @Override
    public List<String> getAccStatement(Account account) {
        return super.getAccStatement(account);
    }

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
}
