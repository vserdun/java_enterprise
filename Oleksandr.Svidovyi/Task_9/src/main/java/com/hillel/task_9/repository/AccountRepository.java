package com.hillel.task_9.repository;

import com.hillel.task_9.model.AccountEntity;

public interface AccountRepository {
    AccountEntity getAccountById(int id);

    void save(AccountEntity accountEntity);

    void update(AccountEntity accountEntity);

    void deleteAccountById(int id);

}
