package com.hillel.mvc.bank_service.service;

import com.hillel.mvc.bank_service.model.AccountEntity;

import java.util.List;

public interface AccountService {
    void addAccount (AccountEntity accountEntity);
    List<AccountEntity> getAccounts();
    AccountEntity getAccount(int id);
    void updateAccount(AccountEntity accountEntity);
    void deleteAccount (int id);
}
