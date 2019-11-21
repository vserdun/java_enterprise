package com.hillel.service.system_mode;

import com.hillel.model.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Primary
public class BankOperationTestMode extends BankOperationMode {

    private final String message = "%s OPERATION IS UNAVAILABLE IN THE TEST MODE";

    @Override
    public void withdraw(BankAccount bankAccount, float withdrawAmount) {
        log.info(String.format("%s tried to withdraw %f", bankAccount.getUserName(), withdrawAmount));
        log.warn(String.format(message, "WITHDRAW"));
    }

    @Override
    public void topUp(BankAccount bankAccount, float topUpAmount) {
        log.info(String.format("%s tried to top-up for %f", bankAccount.getUserName(), topUpAmount));
        log.warn(String.format(message, "TOP-UP"));
    }

    @Override
    public void transfer(BankAccount fromAcc, BankAccount toAcc, float transferAmount) {
        log.info(String.format("%s tried to send %f to %s", fromAcc.getUserName(), transferAmount, toAcc.getUserName()));
        log.warn(String.format(message, "TRANSFER"));
    }
}
