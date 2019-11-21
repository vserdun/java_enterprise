package com.hillel.service;

import com.hillel.model.BankAccount;
import com.hillel.model.SingleAccountOperationType;

public interface BankOperationService {
    void processSingleAccountMoneyOperation(SingleAccountOperationType operation, BankAccount account, float moneyAmount);
    void processSingleAccountInfoOperation(SingleAccountOperationType operation, BankAccount account);
    void processTransfer(BankAccount fromAcc, BankAccount toAcc, float transferAmount);
}
