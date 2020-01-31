package com.hillel.task_9.service;


import com.hillel.task_9.model.AccountEntity;

public interface AccountService {

    void saveAccount(AccountEntity accountEntity);

    void removeAccount(AccountEntity accountEntity);

    String moneyTransfer(AccountEntity supplierAcc, AccountEntity recieverAcc, Long payment);

    String replenish(AccountEntity accountEntity, Long payment);

    String withdraw(AccountEntity accountEntity, Long payment);
}
