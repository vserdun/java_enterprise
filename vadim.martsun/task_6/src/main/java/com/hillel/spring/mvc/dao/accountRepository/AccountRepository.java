package com.hillel.spring.mvc.dao.accountRepository;

import com.hillel.spring.mvc.model.Account;
import com.hillel.spring.mvc.model.requests.AccountRequest;

import java.util.List;
import java.util.Map;

public interface AccountRepository {
    List<Account> getAll();
    Account getById(int id);
    boolean save(AccountRequest accountRequest);
    boolean save(Account account);
    boolean update(int id, AccountRequest accountRequest);
    boolean update(int id, Account account);
    boolean delete(int id);
    Map<Integer, Account> getMap();
}
