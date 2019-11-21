package com.hillel.models;

import com.hillel.models.exceptions.BankAccountNotFoundException;
import com.hillel.services.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class Bank {

    @Autowired
    @Qualifier("initBankAccounts")
    private List<BankAccount> accountList;
    @Autowired
    @Qualifier("initStatements")
    private List<Statement> statements;
    @Autowired
    @Qualifier("getProdBankAccountService")
    private BankAccountService bankAccountService;

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
        statements.forEach(statement -> {
            log.info(statement.toString());
        });
    }

    public List<Statement> getStatements() {
        return statements;
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
