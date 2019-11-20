package com.hillel.service.system_mode;

import com.hillel.model.BankAccount;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class BankOperationTestMode extends BankOperationMode {

    private final String message = "%s OPERATION IS UNAVAILABLE IN THE TEST MODE";

    @Override
    public void withdraw(BankAccount bankAccount, float withdrawAmount) {
        eventLogger.log(String.format("%s tried to withdraw %f", bankAccount.getUserName(), withdrawAmount));
        eventLogger.log(String.format(message, "WITHDRAW"));
    }

    @Override
    public void topUp(BankAccount bankAccount, float topUpAmount) {
        eventLogger.log(String.format("%s tried to top-up for %f", bankAccount.getUserName(), topUpAmount));
        eventLogger.log(String.format(message, "TOP-UP"));
    }

    @Override
    public void transfer(BankAccount fromAcc, BankAccount toAcc, float transferAmount) {
        eventLogger.log(String.format("%s tried to send %f to %s", fromAcc.getUserName(), transferAmount, toAcc.getUserName()));
        eventLogger.log(String.format(message, "TRANSFER"));
    }
}
