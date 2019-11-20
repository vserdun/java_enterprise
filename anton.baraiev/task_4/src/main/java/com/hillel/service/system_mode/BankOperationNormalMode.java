package com.hillel.service.system_mode;

import com.hillel.model.BankAccount;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Primary
public class BankOperationNormalMode extends BankOperationMode {

    @Override
    public void withdraw(BankAccount bankAccount, float withdrawAmount) {
        float difference = bankAccount.getMoneyAmount() - withdrawAmount;
        if (difference >= 0) {
            bankAccount.setMoneyAmount(bankAccount.getMoneyAmount() - withdrawAmount);
            bankAccount.getOperations().add(String.format("%s: withdrew %f", LocalDateTime.now(), withdrawAmount));
            eventLogger.log(String.format("%s withdrew %f", bankAccount.getUserName(), withdrawAmount));
        }else {
            eventLogger.log(String.format("Not enough money to withdraw for user %s", bankAccount.getUserName()));
        }
    }

    @Override
    public void topUp(BankAccount bankAccount, float topUpAmount) {
        bankAccount.setMoneyAmount(bankAccount.getMoneyAmount() + topUpAmount);
        bankAccount.getOperations().add(String.format("%s: top-uped for %f", LocalDateTime.now(), topUpAmount));
        eventLogger.log(String.format("%s top-uped for %f", bankAccount.getUserName(), topUpAmount));
    }

    @Override
    public void transfer(BankAccount fromAcc, BankAccount toAcc, float transferAmount) {
        float difference = fromAcc.getMoneyAmount() - transferAmount;
        if (difference >= 0) {
            fromAcc.setMoneyAmount(fromAcc.getMoneyAmount() - transferAmount);
            fromAcc.getOperations().add(String.format("%s: Sent %f to %s", LocalDateTime.now(), transferAmount, toAcc.getUserName()));
            eventLogger.log(String.format("%s sent %f to %s", fromAcc.getUserName(), transferAmount, toAcc.getUserName()));
            toAcc.setMoneyAmount(toAcc.getMoneyAmount() + transferAmount);
            toAcc.getOperations().add(String.format("%s: Received %f from %s", LocalDateTime.now(), transferAmount, fromAcc.getUserName()));
            eventLogger.log(String.format("%s received %f from %s", toAcc.getUserName(), transferAmount, fromAcc.getUserName()));
        }else {
            eventLogger.log(String.format("Not enough money for user %s to transfer", fromAcc.getUserName()));
        }
    }
}
