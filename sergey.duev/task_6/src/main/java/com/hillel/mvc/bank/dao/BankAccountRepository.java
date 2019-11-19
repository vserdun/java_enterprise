package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.model.BankAccount;

public interface BankAccountRepository {

    void addBankAccount(BankAccount bankAccount);

    void updateBankAccount(BankAccount bankAccount);

    void deleteBankAccount(long id);

    BankAccount getBankAccount(long id);
}
