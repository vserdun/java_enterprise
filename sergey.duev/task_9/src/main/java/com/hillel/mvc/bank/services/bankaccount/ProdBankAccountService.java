package com.hillel.mvc.bank.services.bankaccount;

import com.hillel.mvc.bank.models.entities.BankAccountEntity;
import com.hillel.mvc.bank.models.Statement;
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
    public Statement withdrawMoney(BankAccountEntity bankAccount, double amount) {
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        return addWithdrawMoneyStatement(bankAccount.getId(), amount);
    }

    @Override
    public Statement transferMoney(BankAccountEntity bankAccountFrom, BankAccountEntity bankAccountTo, double amount) {
        withdrawMoney(bankAccountFrom, amount);
        putMoney(bankAccountTo, amount);
        return addStatement(bankAccountFrom.getId(), bankAccountTo.getId(), amount);
    }

    @Override
    public Statement putMoney(BankAccountEntity bankAccount, double amount) {
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        return addPutMoneyStatement(bankAccount.getId(), amount);
    }

    @Override
    public double getBalance(BankAccountEntity bankAccount) {return bankAccount.getBalance();}

    @Override
    public List<Statement> printStatement(BankAccountEntity bankAccount) {
        List<Statement> statements = new ArrayList<>();
        statementList.forEach(statement -> {
            if (statement.getFromId() == bankAccount.getId() || statement.getToId() == bankAccount.getId()) {
                statements.add(statement);
                log.info("Bank Account {} statement -> {}", bankAccount.getId(), statement.toString());
            }
        });
        return statements;
    }

    @Override
    public List<Statement> printStatements() {
        statementList.forEach(statement -> {
            log.info("Statement -> {}", statement.toString());
        });
        return statementList;
    }

    private Statement addPutMoneyStatement(long bankAccountId, double amount) {
        return addStatement(bankAccountId, 0, amount);
    }

    private Statement addWithdrawMoneyStatement(long bankAccountId, double amount) {
        return addStatement(0, bankAccountId, amount);
    }

    protected Statement addStatement(long bankAccountIdFrom, long bankAccountIdTo, double amount) {
        Statement statement = new Statement(bankAccountIdFrom, bankAccountIdTo, LocalDate.now(), amount);
        statementList.add(statement);
        return statement;
    }
}
