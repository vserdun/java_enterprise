package com.hillel.services;

import com.hillel.models.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestBankAccountService extends ProdBankAccountService {

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
