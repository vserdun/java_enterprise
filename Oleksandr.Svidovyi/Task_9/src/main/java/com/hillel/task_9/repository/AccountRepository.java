package com.hillel.task_9.repository;

import com.hillel.task_9.model.AccountEntity;

import java.util.List;

public interface AccountRepository {
    AccountEntity getAccountById(int id);

    void save(AccountEntity accountEntity);

    void update(AccountEntity accountEntity);

    void deleteAccountById(int id);

    List<AccountEntity> getAccountsByClientId(int clientId);
}
