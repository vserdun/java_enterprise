package com.hillel.models;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class BankDataInitializer {

    public List<BankAccount> initBankAccounts() {
        List<BankAccount> accounts = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            accounts.add(new BankAccount(i, LocalDate.now(), String.format("User%d", i), 0));
        }
        return accounts;
    }

    public List<Statement> initStatements(List<BankAccount> bankAccounts) {
        List<Statement> statements = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i < 10; i++) {
            BankAccount bankAccountFrom = bankAccounts.get(random.nextInt((bankAccounts.size() - 1)));
            BankAccount bankAccountTo = bankAccounts.get(random.nextInt((bankAccounts.size() - 1)));
            statements.add(new Statement(bankAccountFrom.getId(), bankAccountTo.getId(), LocalDate.now(), random.nextDouble()));
        }
        return statements;
    }
}
