package com.hillel.service.bank.impl;

import com.hillel.model.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BankServiceTesting extends AbstractBankService {
    @Override
    public boolean withdrawMoney(int amount, BankAccount bankAccount) {
        log.info("Operation withdraw is forbidden in test version");
        return false;
    }

    @Override
    public boolean replenishAccount(int amount, BankAccount bankAccount) {
        log.info("Operation replenish is forbidden in test version");
        return false;
    }

    @Override
    public boolean transferMoney(int amount, BankAccount accountFrom, BankAccount accountTo) {
        log.info("Operation transfer is forbidden in test version");
        return false;
    }
}
