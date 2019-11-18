package com.hillel.mvc.bank_service.service;

import com.hillel.mvc.bank_service.dao.AccountRepository;
import com.hillel.mvc.bank_service.model.AccountEntity;
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
        accountRepository.update(accountEntity);
    }

    @Override
    public void deleteAccount(int id) {
        accountRepository.delete(id);
    }
}
