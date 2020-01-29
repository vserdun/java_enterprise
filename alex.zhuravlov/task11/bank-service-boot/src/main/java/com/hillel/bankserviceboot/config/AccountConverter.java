package com.hillel.bankserviceboot.config;

import com.hillel.bankserviceboot.model.AccountEntity;
import com.hillel.bankserviceboot.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountConverter implements Converter<String, AccountEntity> {

    @Autowired
    private AccountService accountService;

    @Override
    public AccountEntity convert(String source) {
        int accountId = Integer.parseInt(source);
        AccountEntity accountEntity = accountService.getAccount(accountId);
        log.info("Successfully _______ converted accountId to account id = {}", accountEntity.getAccountId());
        return accountEntity;
    }
}
