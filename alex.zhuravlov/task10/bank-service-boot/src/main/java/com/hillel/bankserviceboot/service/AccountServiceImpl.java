package com.hillel.bankserviceboot.service;


import com.hillel.bankserviceboot.dao.AccountRepository;
import com.hillel.bankserviceboot.model.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void addAccount(AccountEntity accountEntity) {
        accountRepository.save(accountEntity);
    }

    @Override
    public List<AccountEntity> getAccounts() {
        return accountRepository.getAccountsList();
    }

    @Override
    public AccountEntity getAccount(int id) {
        return accountRepository.getAccountEntityById(id);
    }

    @Override
    public void updateAccount(AccountEntity accountEntity) {
        accountRepository.save(accountEntity);
    }

    @Override
    public void deleteAccount(int id) {
        accountRepository.delete(id);
    }

    @Override
    public Map<String, String> getAccontsMap() {
        List<AccountEntity> accounts = getAccounts();
        Map<String, String> accountMap = new HashMap<>();
        for (AccountEntity account : accounts) {
            String id = String.valueOf(account.getAccountId());
            accountMap.put(id, id);
        }
        return accountMap;
    }


}
