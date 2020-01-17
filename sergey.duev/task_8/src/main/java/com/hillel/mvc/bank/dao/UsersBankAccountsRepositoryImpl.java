package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.BankAccount;
import com.hillel.mvc.bank.models.exceptions.BankAccountNotFoundException;
import com.hillel.mvc.bank.models.exceptions.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UsersBankAccountsRepositoryImpl implements UsersBankAccountsRepository {

    private Map<Long, Map<Long, BankAccount>> usersBankAccounts = new ConcurrentHashMap<>();
    private long nextId = 1L;

    @Override
    public void addUserBankAccount(long userId, BankAccount bankAccount) {
        usersBankAccounts.putIfAbsent(userId, new ConcurrentHashMap<>()).put(nextId, bankAccount);
        nextId++;
    }

    @Override
    public void updateUserBankAccount(long userId, long bankAccountId, BankAccount bankAccount) throws UserNotFoundException, BankAccountNotFoundException {
        throwExceptionIfBankAccountNotExists(userId, bankAccountId);
        usersBankAccounts.get(userId).put(bankAccountId, bankAccount);
    }

    @Override
    public void deleteUserBankAccount(long userId, long bankAccountId) throws UserNotFoundException, BankAccountNotFoundException {
        throwExceptionIfBankAccountNotExists(userId, bankAccountId);
        usersBankAccounts.get(userId).remove(bankAccountId);
    }

    @Override
    public List<BankAccount> getUserBankAccounts(long userId) throws UserNotFoundException {
        throwExceptionIfUserNotExists(userId);
        return new ArrayList<>(usersBankAccounts.get(userId).values());
    }

    @Override
    public BankAccount getUserBankAccount(long userId, long bankAccountId) throws UserNotFoundException, BankAccountNotFoundException{
        throwExceptionIfBankAccountNotExists(userId, bankAccountId);
        return usersBankAccounts.get(userId).get(bankAccountId);
    }

    @Override
    public BankAccount getBankAccount(long bankAccountId) throws BankAccountNotFoundException {
        for (Map<Long, BankAccount> bankAccountMap : usersBankAccounts.values()) {
            if (bankAccountMap.keySet().contains(bankAccountId)) {
                return bankAccountMap.get(bankAccountId);
            }
        }
        throw new BankAccountNotFoundException(bankAccountId);
    }

    private void throwExceptionIfBankAccountNotExists(long userId, long bankAccountId) throws UserNotFoundException, BankAccountNotFoundException{
       throwExceptionIfUserNotExists(userId);
       if (!usersBankAccounts.get(userId).containsKey(bankAccountId)) {
           throw new BankAccountNotFoundException(bankAccountId);
       }
    }

    private void  throwExceptionIfUserNotExists(long userId) throws UserNotFoundException {
        if (!usersBankAccounts.containsKey(userId)) {
            throw new UserNotFoundException(userId);
        }
    }
}
