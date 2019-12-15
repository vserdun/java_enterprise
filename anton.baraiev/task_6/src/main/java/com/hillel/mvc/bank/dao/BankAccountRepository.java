package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.model.BankAccount;

import java.util.List;

public interface BankAccountRepository {

    List<BankAccount> getAccounts();

    BankAccount getAccountById(long id);

    void addAccount(BankAccount account);

    void updateAccountInfo(BankAccount account);

    void deleteAccount(long id);
}
