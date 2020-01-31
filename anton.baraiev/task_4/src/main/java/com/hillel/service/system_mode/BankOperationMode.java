package com.hillel.service.system_mode;

import com.hillel.model.BankAccount;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BankOperationMode {

    public abstract void withdraw(BankAccount bankAccount, float withdrawAmount);
    public abstract void topUp(BankAccount bankAccount, float topUpAmount);
    public abstract void transfer(BankAccount fromAcc, BankAccount toAcc, float transferAmount);

    public void showBalance(BankAccount bankAccount) {
        log.info(String.format("%s current balance: %s", bankAccount.getUserName(), bankAccount.getMoneyAmount()));
    }

    public void showStatement(BankAccount bankAccount) {
        log.info(String.format("%s account operations:", bankAccount.getUserName()));
        for (String s : bankAccount.getOperations()) log.info("\t" + s);
    }
}
