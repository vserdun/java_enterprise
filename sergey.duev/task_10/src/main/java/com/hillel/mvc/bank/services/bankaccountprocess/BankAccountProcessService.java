package com.hillel.mvc.bank.services.bankaccountprocess;

import com.hillel.mvc.bank.models.Statement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankAccountProcessService {

    Statement withdrawMoney(long id, double amount);

    Statement transferMoney(long bankAccountFrom, long bankAccountTo, double amount);

    Statement putMoney(long bankAccount, double amount);

    double getBalance(long bankAccount);

    List<Statement> printStatement(long bankAccount);

    List<Statement> printStatements();
}
