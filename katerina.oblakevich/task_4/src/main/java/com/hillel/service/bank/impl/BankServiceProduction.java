package com.hillel.service.bank.impl;

import com.hillel.model.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BankServiceProduction extends AbstractBankService {

    @Override
    public boolean withdrawMoney(int amount, BankAccount bankAccount) {
        if (isValidAccountForOperation(bankAccount) && isValidAmountForOperation(amount)) {
            if ((amount < bankAccount.getAmount())) {
                bankAccount.setAmount(bankAccount.getAmount() - amount);
                return true;
            }
            log.info("Account amount less then withdraw amount");
        }
        return false;
    }

    @Override
    public boolean replenishAccount(int amount, BankAccount bankAccount) {
        if (isValidAccountForOperation(bankAccount) && isValidAmountForOperation(amount)) {
            bankAccount.setAmount(bankAccount.getAmount() + amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean transferMoney(int amount, BankAccount accountFrom, BankAccount accountTo) {
        if (isValidAccountForOperation(accountFrom)
                && isValidAccountForOperation(accountTo)
                && isValidAmountForOperation(amount)) {
            withdrawMoney(amount, accountFrom);
            replenishAccount(amount, accountTo);
            return true;
        }
        return false;
    }

    private boolean isValidAmountForOperation(int amount) {
        if (amount < 0) {
            log.info("Amount can not be less then 0");
            return false;
        }
        return true;
    }

    private boolean isValidAccountForOperation(BankAccount bankAccount) {
        if (bankAccount == null) {
            log.info("Bank account is null");
            return false;
        }
        return true;
    }
}
