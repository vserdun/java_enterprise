package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.model.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

    private Map<Long, BankAccount> accounts = new ConcurrentHashMap<>();
    private long currentId = 100_000;

    @Override
    public List<BankAccount> getAccounts() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public BankAccount getAccountById(long id) {
        return accounts.get(id);
    }

    @Override
    public void addAccount(BankAccount account) {
        if (accounts.get(account.getId()) == null) {
            account.setId(currentId);
            currentId++;
        }
        accounts.put(account.getId(), account);
    }

    @Override
    public void updateAccountInfo(BankAccount account) {
        accounts.put(account.getId(), account);
    }

    @Override
    public void deleteAccount(long id) {
        accounts.remove(id);
    }
}
