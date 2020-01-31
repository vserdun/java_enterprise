package com.hillel.mvc.bank.services.bankaccountprocess;

import com.hillel.mvc.bank.dao.BankAccountsRepository;
import com.hillel.mvc.bank.models.entities.BankAccountEntity;
import com.hillel.mvc.bank.models.Statement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProdBankAccountProcessService implements BankAccountProcessService {

    private BankAccountsRepository bankAccountsRepository;
    private List<Statement> statementList = new ArrayList<>();

    @Autowired
    public ProdBankAccountProcessService(BankAccountsRepository bankAccountsRepository) {
        this.bankAccountsRepository = bankAccountsRepository;
    }

    @Override
    @Transactional
    public Statement withdrawMoney(long bankAccountId, double amount) {
        BankAccountEntity bankAccountEntity = bankAccountsRepository.getBankAccount(bankAccountId);
        bankAccountEntity.setBalance(bankAccountEntity.getBalance() - amount);
        return addWithdrawMoneyStatement(bankAccountId, amount);
    }

    @Override
    @Transactional
    public Statement transferMoney(long bankAccountFrom, long bankAccountTo, double amount) {
        withdrawMoney(bankAccountFrom, amount);
        putMoney(bankAccountTo, amount);
        return addStatement(bankAccountFrom, bankAccountTo, amount);
    }

    @Override
    @Transactional
    public Statement putMoney(long bankAccount, double amount) {
        BankAccountEntity entity = bankAccountsRepository.getBankAccount(bankAccount);
        entity.setBalance(entity.getBalance() + amount);
        return addPutMoneyStatement(bankAccount, amount);
    }

    @Override
    @Transactional
    public double getBalance(long bankAccount) {
        BankAccountEntity entity = bankAccountsRepository.getBankAccount(bankAccount);
        return entity.getBalance();
    }

    @Override
    @Transactional
    public List<Statement> printStatement(long bankAccount) {
        List<Statement> statements = new ArrayList<>();
        statementList.forEach(statement -> {
            if (statement.getFromId() == bankAccount || statement.getToId() == bankAccount) {
                statements.add(statement);
                log.info("Bank Account {} statement -> {}", bankAccount, statement.toString());
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

