package com.hillel.bankserviceboot.dao;

import com.hillel.bankserviceboot.model.AccountEntity;

import java.util.List;

public interface AccountRepository {
    List<AccountEntity> getAccountsList();
    AccountEntity getAccountEntityById(int id);
    void save(AccountEntity accountEntity);
    void update(AccountEntity accountEntity);
    void delete(int id);
}
