package com.hillel.service;

import com.hillel.model.BankAccount;
import com.hillel.model.SingleAccountOperationType;
import com.hillel.service.system_mode.BankOperationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankOperationServiceImpl implements BankOperationService {

    @Autowired
    private BankOperationMode bankOperationMode;

    @Override
    public void processSingleAccountMoneyOperation(SingleAccountOperationType operation, BankAccount account, float moneyAmount) {
        switch (operation) {
            case TOP_UP:
                bankOperationMode.topUp(account, moneyAmount);
                break;
            case WITHDRAW:
                bankOperationMode.withdraw(account, moneyAmount);
                break;
        }
    }

    @Override
    public void processSingleAccountInfoOperation(SingleAccountOperationType operation, BankAccount account) {
        switch (operation) {
            case SHOW_BALANCE:
                bankOperationMode.showBalance(account);
                break;
            case SHOW_STATEMENT:
                bankOperationMode.showStatement(account);
                break;
        }
    }

    @Override
    public void processTransfer(BankAccount fromAcc, BankAccount toAcc, float transferAmount) {
        bankOperationMode.transfer(fromAcc, toAcc, transferAmount);
    }
}
