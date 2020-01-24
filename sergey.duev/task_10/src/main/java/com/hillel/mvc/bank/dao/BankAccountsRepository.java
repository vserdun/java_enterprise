package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.entities.BankAccountEntity;
import com.hillel.mvc.bank.models.entities.CardEntity;

public interface BankAccountsRepository {

    void addUserBankAccount(BankAccountEntity bankAccount);

    void updateUserBankAccount(BankAccountEntity bankAccount);

    void deleteUserBankAccount(long userId, long bankAccountId);

    BankAccountEntity getUserBankAccount(long userId, long bankAccountId);

    BankAccountEntity getBankAccount(long bankAccountId);
}
