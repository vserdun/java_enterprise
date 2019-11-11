package com.hillel.service;

import com.hillel.model.Account;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestAccountService extends AccountServiceImpl {

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
