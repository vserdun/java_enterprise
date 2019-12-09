package com.hillel.task_6.service;

import com.hillel.task_6.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private ClientService clientService;


    @Override
    public String moneyTransfer(Account supplierAcc, Account recieverAcc, Long payment) {
        recieverAcc.setBalance(recieverAcc.getBalance() + payment);
        supplierAcc.setBalance(supplierAcc.getBalance() - payment);

        return "You have payed " + payment + " " + recieverAcc.getCurrency() + ".";
    }


    @Override
    public String replenish(Account account, Long payment) {
        account.setBalance(account.getBalance() + payment);

        return "You have replenished your account with " + payment + " " + account.getCurrency() + ".";
    }


    @Override
    public String withdraw(Account account, Long payment) {
        account.setBalance(account.getBalance() - payment);

        return "You have withdrawn " + payment + " " + account.getCurrency() + " from your account.";
    }
}
