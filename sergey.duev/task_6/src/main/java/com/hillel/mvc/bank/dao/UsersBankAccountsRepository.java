package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.BankAccount;
import com.hillel.mvc.bank.models.User;
import com.hillel.mvc.bank.models.exceptions.BankAccountNotFoundException;
import com.hillel.mvc.bank.models.exceptions.UserNotFoundException;

import java.util.List;

public interface UsersBankAccountsRepository {

    void addUserBankAccount(long userId, BankAccount bankAccount);

    void updateUserBankAccount(long userId, long bankAccountId, BankAccount bankAccount) throws UserNotFoundException, BankAccountNotFoundException;

    void deleteUserBankAccount(long userId, long bankAccountId) throws UserNotFoundException, BankAccountNotFoundException;

    List<BankAccount> getUserBankAccounts(long userId) throws UserNotFoundException;

    BankAccount getUserBankAccount(long userId, long bankAccountId) throws UserNotFoundException, BankAccountNotFoundException;

    BankAccount getBankAccount(long bankAccountId) throws BankAccountNotFoundException;




}
