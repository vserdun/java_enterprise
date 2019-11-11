package com.hillel;

import com.hillel.model.Account;
import com.hillel.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountServiceDemo {

    @Autowired
    @Qualifier("testAccountService")
    private AccountService accountService;

    public void demoRun() {
        Account account1 = new Account(1, "Alex", 0.0, new ArrayList<>());
        Account account2 = new Account(2, "Ben", 0.0, new ArrayList<>());
        Account account3 = new Account(3, "Sam", 0.0, new ArrayList<>());

        accountService.deposit(account1, 100.0);
        accountService.deposit(account2, 340.0);
        accountService.deposit(account3, 1000.0);

        accountService.transfer(account1, account2, 50);

        accountService.getAccStatement(account2);
    }
}
