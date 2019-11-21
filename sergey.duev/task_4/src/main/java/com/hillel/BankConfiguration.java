package com.hillel;

import com.hillel.models.Bank;
import com.hillel.models.BankAccount;
import com.hillel.models.Statement;
import com.hillel.services.ProdBankAccountService;
import com.hillel.services.TestBankAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@ComponentScan("com.hillel.models")
public class BankConfiguration {

    @Bean
    public ProdBankAccountService getProdBankAccountService(Bank bank) {
        return new ProdBankAccountService(bank.getStatements());
    }

    @Bean
    public TestBankAccountService getTestBankAccountService(Bank bank) {
        return new TestBankAccountService(bank.getStatements());
    }

    @Bean
    public List<BankAccount> initBankAccounts() {
        List<BankAccount> accounts = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            accounts.add(new BankAccount(i, LocalDate.now(), String.format("User%d", i)));
        }
        return accounts;
    }

    @Bean
    public List<Statement> initStatements(Bank bank) {
        List<Statement> statements = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i < 10; i++) {
            BankAccount bankAccountFrom = bank.getBankAccounts().get(random.nextInt((bank.getBankAccounts().size() - 1)));
            BankAccount bankAccountTo = bank.getBankAccounts().get(random.nextInt((bank.getBankAccounts().size() - 1)));
            statements.add(new Statement(bankAccountFrom.getId(), bankAccountTo.getId(), LocalDate.now(), random.nextDouble()));
        }
        return statements;
    }

    @Bean
    public List<Statement> getBankStatements(Bank bank) {
        return bank.getStatements();
    }
}
