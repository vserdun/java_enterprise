package com.hillel.mvc.bank.service.account_service;

import com.hillel.mvc.bank.model.BankAccount;
import java.util.List;

public interface AccountOperationModeService {

    List<BankAccount> getAccounts();

    BankAccount getAccountById(long id);

    void addAccount(BankAccount account);

    void updateAccountInfo(BankAccount account);

    void deleteAccount(long id);

    boolean isTestMode();
}
