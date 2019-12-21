package com.hillel.mvc.bank.services.bankaccount;

import com.hillel.mvc.bank.models.BankAccount;
import com.hillel.mvc.bank.models.Statement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankAccountService {

    Statement withdrawMoney(BankAccount bankAccount, double amount);

    Statement transferMoney(BankAccount bankAccountFrom, BankAccount bankAccountTo, double amount);

    Statement putMoney(BankAccount bankAccount, double amount);

    double getBalance(BankAccount bankAccount);

    List<Statement> printStatement(BankAccount bankAccount);

    List<Statement> printStatements();
}
