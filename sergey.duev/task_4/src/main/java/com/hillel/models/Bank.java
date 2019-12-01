package com.hillel.models;

import com.hillel.models.exceptions.BankAccountNotFoundException;
import com.hillel.services.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Bank {

    @Autowired
    private BankDataInitializer bankDataInitializer;

    private List<BankAccount> accountList;

    @Autowired
    @Qualifier("prodBankAccountService")
    private BankAccountService bankAccountService;

    public List<BankAccount> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<BankAccount> accountList) {
        this.accountList = accountList;
    }

    public void createBankAccount(BankAccount bankAccount) {
        accountList.add(bankAccount);
    }

    public void withdrawMoney(long bankAccountId, double amount)  throws BankAccountNotFoundException{
        bankAccountService.withdrawMoney(findBankAccount(bankAccountId), amount);
    }

    public void transferMoney(long bankAccountIdFrom, long bankAccountIdTo, double amount) throws BankAccountNotFoundException {
        bankAccountService.transferMoney(findBankAccount(bankAccountIdFrom), findBankAccount(bankAccountIdTo), amount);
    }

    public void putMoney(long bankAccountId, double amount) throws BankAccountNotFoundException{
        bankAccountService.putMoney(findBankAccount(bankAccountId), amount);
    }

    public void printStatement(long bankAccountId) throws BankAccountNotFoundException {
        bankAccountService.printStatement(findBankAccount(bankAccountId));
    }

    public void printStatements() {
        bankAccountService.printStatements();
    }

    public double getBalance(long bankAccountId) throws BankAccountNotFoundException {
        return bankAccountService.getBalance(findBankAccount(bankAccountId));
    }

    public List<BankAccount> getBankAccounts() {
        return accountList;
    }

    private BankAccount findBankAccount(long bankAccountId) throws BankAccountNotFoundException {
        for (BankAccount bankAccount : accountList) {
            if (bankAccount.getId() == bankAccountId) return accountList.get(accountList.indexOf(bankAccount));
        }
        throw new BankAccountNotFoundException(bankAccountId);
    }
}
