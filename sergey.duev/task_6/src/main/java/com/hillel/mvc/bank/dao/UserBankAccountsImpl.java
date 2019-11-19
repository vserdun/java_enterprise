package com.hillel.mvc.bank.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserBankAccountsImpl implements UserBankAccounts {

    private Map<Long, List<Long>> userBankAccounts = new ConcurrentHashMap<>();

    @Override
    public void addUserBankAccount(long userId, long bankAccountId) {
        userBankAccounts.putIfAbsent(userId, new ArrayList<>()).add(bankAccountId);
    }

    @Override
    public void deleteUserBankAccount(long userId, long bankAccountId) {
        userBankAccounts.putIfAbsent(userId, new ArrayList<>()).remove(bankAccountId);
    }

    @Override
    public List<Long> getUserBankAccounts(long userId) {
        return userBankAccounts.putIfAbsent(userId, new ArrayList<>());
    }
}
