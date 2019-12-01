package com.hillel.services;

import com.hillel.models.BankAccount;
import com.hillel.models.Statement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProdBankAccountService implements BankAccountService {

    private List<Statement> statementList = new ArrayList<>();

    @Override
    public void withdrawMoney(BankAccount bankAccount, double amount) {
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        addWithdrawMoneyStatement(bankAccount.getId(), amount);
    }

    @Override
    public void transferMoney(BankAccount bankAccountFrom, BankAccount bankAccountTo, double amount) {
        withdrawMoney(bankAccountFrom, amount);
        putMoney(bankAccountTo, amount);
        addStatement(bankAccountFrom.getId(), bankAccountTo.getId(), amount);
    }

    @Override
    public void putMoney(BankAccount bankAccount, double amount) {
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        addPutMoneyStatement(bankAccount.getId(), amount);
    }

    @Override
    public double getBalance(BankAccount bankAccount) {return bankAccount.getBalance();}

    @Override
    public void printStatement(BankAccount bankAccount) {
        statementList.forEach(statement -> {
            if (statement.getFromId() == bankAccount.getId() || statement.getToId() == bankAccount.getId()) {
                log.info("Bank Account {} statement -> {}", bankAccount.getId(), statement.toString());
            }
        });
    }

    @Override
    public void printStatements() {
        statementList.forEach(statement -> {
            log.info("Statement -> {}", statement.toString());
        });
    }

    private void addPutMoneyStatement(long bankAccountId, double amount) {
        addStatement(bankAccountId, 0, amount);
    }

    private void addWithdrawMoneyStatement(long bankAccountId, double amount) {
        addStatement(0, bankAccountId, amount);
    }

    protected void addStatement(long bankAccountIdFrom, long bankAccountIdTo, double amount) {
        statementList.add(new Statement(bankAccountIdFrom, bankAccountIdTo, LocalDate.now(), amount));
    }
}
