package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.model.BankAccount;

import java.util.List;

public interface BankAccountRepository {

    long addBankAccount(BankAccount bankAccount);

    void updateBankAccount(BankAccount bankAccount);

    void deleteBankAccount(long id);

    BankAccount getBankAccount(long id);

    List<BankAccount> getAllBankAccounts();
}
