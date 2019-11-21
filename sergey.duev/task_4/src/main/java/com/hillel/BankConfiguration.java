package com.hillel;

import com.hillel.models.Bank;
import com.hillel.models.BankAccount;
import com.hillel.models.BankDataInitializer;
import com.hillel.models.Statement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan("com.hillel")
public class BankConfiguration {

    @Bean
    public BankDataInitializer bankDataInitializer() {
        return new BankDataInitializer();
    }

    @Bean
    public List<BankAccount> initBankAccounts() {
        return bankDataInitializer().initBankAccounts();
    }

    @Bean
    public List<Statement> initBankStatements(Bank bank) {
        return bankDataInitializer().initStatements(bank.getBankAccounts());
    }


}
