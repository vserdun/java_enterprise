package com.hillel.task_6.service;

import com.hillel.task_6.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestAccountServiceImpl implements AccountService {
    @Autowired
    private ClientService clientService;


    @Override
    public String moneyTransfer(Account supplierAcc, Account recieverAcc, Long payment) {
        return "Service is in testing regime. Transactions are currently disabled. Sorry for temporary difficulties.";
    }


    @Override
    public String replenish(Account account, Long payment) {
        return "Service is in testing regime. Replenish function is currently disabled. Sorry for temporary difficulties.";
    }


    @Override
    public String withdraw(Account account, Long payment) {
        return "Service is in testing regime. Withdraw function is currently disabled. Sorry for temporary difficulties.";
    }
}
