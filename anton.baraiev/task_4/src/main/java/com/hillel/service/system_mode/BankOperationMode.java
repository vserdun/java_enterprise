package com.hillel.service.system_mode;

import com.hillel.model.BankAccount;
import com.hillel.service.log.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BankOperationMode {

    @Autowired
    EventLogger eventLogger;

    public abstract void withdraw(BankAccount bankAccount, float withdrawAmount);
    public abstract void topUp(BankAccount bankAccount, float topUpAmount);
    public abstract void transfer(BankAccount fromAcc, BankAccount toAcc, float transferAmount);

    public void showBalance(BankAccount bankAccount) {
        eventLogger.log(String.format("%s current balance: %s", bankAccount.getUserName(), bankAccount.getMoneyAmount()));
    }

    public void showStatement(BankAccount bankAccount) {
        eventLogger.log(String.format("%s account operations:", bankAccount.getUserName()));
        for (String s : bankAccount.getOperations()) eventLogger.log("\t" + s);
    }
}
