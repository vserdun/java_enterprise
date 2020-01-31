package com.hillel.spring.mvc.model.mappers.accountMapper;

import com.hillel.spring.mvc.model.Account;
import com.hillel.spring.mvc.model.requests.AccountRequest;

public interface AccountMapper {
    Account getAccount(AccountRequest request);
}
