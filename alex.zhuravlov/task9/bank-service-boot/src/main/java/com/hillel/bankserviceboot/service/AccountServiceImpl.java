package com.hillel.bankserviceboot.service;


import com.hillel.bankserviceboot.dao.AccountRepository;
import com.hillel.bankserviceboot.model.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
