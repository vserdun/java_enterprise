package com.hillel.task_9.service;

import com.hillel.task_9.model.AccountEntity;
import com.hillel.task_9.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
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

        if (accountRepository.getAccountById(accountEntity.getId()) != null) {
            accountRepository.deleteAccountById(accountEntity.getId());
        }
    }


    @Override
    public String moneyTransfer(AccountEntity supplierAcc, AccountEntity recieverAcc, Long payment) {
        recieverAcc.setBalance(recieverAcc.getBalance() + payment);
        supplierAcc.setBalance(supplierAcc.getBalance() - payment);

        accountRepository.update(recieverAcc);
        accountRepository.update(supplierAcc);

        return "You have payed " + payment + " " + recieverAcc.getCurrency() + ".";
    }


    @Override
    public String replenish(AccountEntity accountEntity, Long payment) {
        accountEntity.setBalance(accountEntity.getBalance() + payment);
        accountRepository.update(accountEntity);

        return "You have replenished your accountEntity with " + payment + " " + accountEntity.getCurrency() + ".";
    }


    @Override
    public String withdraw(AccountEntity accountEntity, Long payment) {
        accountEntity.setBalance(accountEntity.getBalance() - payment);
        accountRepository.update(accountEntity);

        return "You have withdrawn " + payment + " " + accountEntity.getCurrency() + " from your accountEntity.";
    }

}
