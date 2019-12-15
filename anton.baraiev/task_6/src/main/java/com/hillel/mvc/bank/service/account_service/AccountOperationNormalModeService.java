package com.hillel.mvc.bank.service.account_service;

import com.hillel.mvc.bank.dao.BankAccountRepository;
import com.hillel.mvc.bank.model.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class AccountOperationNormalModeService implements AccountOperationModeService {

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
        accountRepository.addAccount(account);
    }

    @Override
    public void updateAccountInfo(BankAccount account) {
        accountRepository.updateAccountInfo(account);
    }

    @Override
    public void deleteAccount(long id) {
        accountRepository.deleteAccount(id);
    }

    @Override
    public boolean isTestMode() { return false; }
}
