package com.hillel.bankserviceboot.service;

import com.hillel.bankserviceboot.model.AccountEntity;
import com.hillel.bankserviceboot.model.UserEntity;

import java.util.List;
import java.util.Map;

public interface AccountService {
    void addAccount(AccountEntity accountEntity);

    List<AccountEntity> getAccounts();

    AccountEntity getAccount(int id);

    void updateAccount(AccountEntity accountEntity);

    void deleteAccount(int id);

    Map<String, String> getAccountsMap();

    List<AccountEntity> getUserAccounts(UserEntity user);
}
