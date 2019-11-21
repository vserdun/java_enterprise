package com.hillel.services;

import com.hillel.models.BankAccount;
import com.hillel.models.Statement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TestBankAccountService extends ProdBankAccountService {

    public TestBankAccountService(List<Statement> statements) {
        super(statements);
    }

    @Override
    public void withdrawMoney(BankAccount bankAccount, double amount) {
       log.info("test withdrawMoney");
    }

    @Override
    public void transferMoney(BankAccount bankAccountFrom, BankAccount bankAccountTo, double amount) {
        log.info("test transferMoney");
    }

    @Override
    public void putMoney(BankAccount bankAccount, double amount) {
        log.info("test putMoney");
    }
}
