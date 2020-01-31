package com.hillel.service.bank.impl;

import com.hillel.model.BankAccount;
import com.hillel.service.bank.BankService;

abstract class AbstractBankService implements BankService {

    @Override
    public long getAmount(BankAccount bankAccount) {
        return bankAccount.getAmount();
    }

    @Override
    public String getAccountStatement(BankAccount bankAccount) {
        return bankAccount.toString();
    }
}
