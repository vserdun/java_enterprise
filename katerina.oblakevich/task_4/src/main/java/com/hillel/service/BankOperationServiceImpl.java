package com.hillel.service;

import com.hillel.model.BankAccount;
import com.hillel.service.bank.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BankOperationServiceImpl implements BankOperationService {
    @Autowired
    @Qualifier("bankServiceProduction")
    private BankService bankService;

//    @Qualifier("bankServiceTesting")
//    private BankService bankService;

    @Override
    public void getAccountAmount(BankAccount bankAccount) {
        log.info(String.valueOf(bankService.getAmount(bankAccount)));
    }

    @Override
    public void getAccountStatement(BankAccount bankAccount) {
        log.info(bankService.getAccountStatement(bankAccount));
    }

    @Override
    public boolean withdrawMoneyFromAccount(int amount, BankAccount bankAccount) {
        return bankService.withdrawMoney(amount, bankAccount);
    }

    @Override
    public boolean replenishAccount(int amount, BankAccount bankAccount) {
        return bankService.replenishAccount(amount, bankAccount);
    }

    @Override
    public boolean transferMoneyToAnotherAccount(int amount, BankAccount accountFrom, BankAccount accountTo) {
        return bankService.transferMoney(amount, accountFrom, accountTo);
    }
}
