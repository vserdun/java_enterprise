package com.hillel.mvc.bank.services.bankaccount;

import com.hillel.mvc.bank.models.entities.BankAccountEntity;
import com.hillel.mvc.bank.models.Statement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankAccountService {

    Statement withdrawMoney(BankAccountEntity bankAccount, double amount);

    Statement transferMoney(BankAccountEntity bankAccountFrom, BankAccountEntity bankAccountTo, double amount);

    Statement putMoney(BankAccountEntity bankAccount, double amount);

    double getBalance(BankAccountEntity bankAccount);

    List<Statement> printStatement(BankAccountEntity bankAccount);

    List<Statement> printStatements();
}
