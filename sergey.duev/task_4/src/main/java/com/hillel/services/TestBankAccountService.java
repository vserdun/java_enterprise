package com.hillel.services;

import com.hillel.models.BankAccount;
import com.hillel.models.Statement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestBankAccountService extends ProdBankAccountService {

    public TestBankAccountService(List<Statement> statements) {
        super(statements);
    }

    @Override
    public void withdrawMoney(BankAccount bankAccount, double amount) {
        System.out.println("test withdrawMoney");
    }

    @Override
    public void transferMoney(BankAccount bankAccountFrom, BankAccount bankAccountTo, double amount) {
        System.out.println("test transferMoney");
    }

    @Override
    public void putMoney(BankAccount bankAccount, double amount) {
        System.out.println("test putMoney");
    }
}
