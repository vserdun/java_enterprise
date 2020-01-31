package com.hillel.bankserviceboot.dao;


import com.hillel.bankserviceboot.model.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private int currentAccountId = 1;
    private Map<Integer, AccountEntity> accountsMap = new ConcurrentHashMap<>();

    @Override
    public List<AccountEntity> getAccountsList() {
        return new ArrayList<>(accountsMap.values());
    }

    @Override
    public AccountEntity getAccountEntityById(int id) {
        return accountsMap.get(id);
    }

    @Override
    public void save(AccountEntity accountEntity) {
        if (accountsMap.get(accountEntity.getAccountId()) == null) {
            accountEntity.setAccountId(currentAccountId);
            currentAccountId++;
        }

        accountsMap.put(accountEntity.getAccountId(), accountEntity);
    }

    @Override
    public void update(AccountEntity accountEntity) {
        accountsMap.put(accountEntity.getAccountId(), accountEntity);
    }

    @Override
    public void delete(int id) {
        accountsMap.remove(id);
    }
}
