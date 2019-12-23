package com.hillel.bankserviceboot.service;

import com.hillel.bankserviceboot.model.AccountEntity;

import java.util.List;

public interface AccountService {
    void addAccount(AccountEntity accountEntity);

    List<AccountEntity> getAccounts();

    AccountEntity getAccount(int id);

    void updateAccount(AccountEntity accountEntity);

    void deleteAccount(int id);
}
