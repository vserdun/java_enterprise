package com.hillel.mvc.bank_service.dao;

import com.hillel.mvc.bank_service.model.AccountEntity;

import java.util.List;

public interface AccountRepository {
    List<AccountEntity> getAccountsList();
    AccountEntity getAccountEntityById (int id);
    void save (AccountEntity accountEntity);
    void update (AccountEntity accountEntity);
    void delete(int id);
}
