package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.entities.BankAccountEntity;
import com.hillel.mvc.bank.models.exceptions.BankAccountNotFoundException;
import com.hillel.mvc.bank.models.exceptions.UserNotFoundException;

public interface UsersBankAccountsRepository {

    void addUserBankAccount(BankAccountEntity bankAccount);

    void updateUserBankAccount(BankAccountEntity bankAccount) throws UserNotFoundException, BankAccountNotFoundException;

    void deleteUserBankAccount(long userId, long bankAccountId) throws UserNotFoundException, BankAccountNotFoundException;

    BankAccountEntity getUserBankAccount(long userId, long bankAccountId) throws UserNotFoundException, BankAccountNotFoundException;

    BankAccountEntity getBankAccount(long bankAccountId) throws BankAccountNotFoundException;
}
