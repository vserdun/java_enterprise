package com.hillel.services;

import com.hillel.models.BankAccount;
import com.hillel.models.Statement;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdBankAccountService implements BankAccountService {

    private List<Statement> statements = new ArrayList<>();

    public ProdBankAccountService(List<Statement> statements) {
        this.statements = statements;
    }

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
        statements.forEach(statement -> {
            if (statement.getFromId() == bankAccount.getId() || statement.getToId() == bankAccount.getId()) {
                System.out.println(statement.toString());
            }
        });
    }

    private void addPutMoneyStatement(long bankAccountId, double amount) {
        addStatement(bankAccountId, 0, amount);
    }

    private void addWithdrawMoneyStatement(long bankAccountId, double amount) {
        addStatement(0, bankAccountId, amount);
    }

    private void addStatement(long bankAccountIdFrom, long bankAccountIdTo, double amount) {
        statements.add(new Statement(bankAccountIdFrom, bankAccountIdTo, LocalDate.now(), amount));
    }
}
