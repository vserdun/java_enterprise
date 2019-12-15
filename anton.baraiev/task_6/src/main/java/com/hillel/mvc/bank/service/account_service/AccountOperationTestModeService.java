package com.hillel.mvc.bank.service.account_service;

import com.hillel.mvc.bank.dao.BankAccountRepository;
import com.hillel.mvc.bank.model.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
//@Primary
public class AccountOperationTestModeService implements AccountOperationModeService {

    @Autowired
    BankAccountRepository accountRepository;

    @Override
    public List<BankAccount> getAccounts() {
        return accountRepository.getAccounts();
    }

    @Override
    public BankAccount getAccountById(long id) {
        return accountRepository.getAccountById(id);
    }

    @Override
    public void addAccount(BankAccount account) {
        log.warn("Adding of a new account is unavailable in the test mode");
    }

    @Override
    public void updateAccountInfo(BankAccount account) {
        log.warn("Updating account info is unavailable in the test mode");
    }

    @Override
    public void deleteAccount(long id) {
        log.warn("Deleting oof an account is unavailable in the test mode");
    }

    @Override
    public boolean isTestMode() { return true; }
}
