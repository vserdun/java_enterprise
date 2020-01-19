package com.hillel.task_9.service;

import com.hillel.task_9.model.AccountEntity;
import com.hillel.task_9.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestAccountServiceImpl implements AccountService {
    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public void saveAccount(AccountEntity accountEntity) {
        accountRepository.save(accountEntity);
    }

    @Override
    public void removeAccount(AccountEntity accountEntity) {
        accountRepository.getAccountById(accountEntity.getId());

        if (accountEntity != null) {
            accountRepository.deleteAccountById(accountEntity.getId());
        }
    }

    @Override
    public String moneyTransfer(AccountEntity supplierAcc, AccountEntity recieverAcc, Long payment) {
        return "Service is in testing regime. Transactions are currently disabled. Sorry for temporary difficulties.";
    }


    @Override
    public String replenish(AccountEntity accountEntity, Long payment) {
        return "Service is in testing regime. Replenish function is currently disabled. Sorry for temporary difficulties.";
    }


    @Override
    public String withdraw(AccountEntity accountEntity, Long payment) {
        return "Service is in testing regime. Withdraw function is currently disabled. Sorry for temporary difficulties.";
    }
}
