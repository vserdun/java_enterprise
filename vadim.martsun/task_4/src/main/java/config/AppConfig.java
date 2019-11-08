package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import random.RandomBankAccountService;
import random.RandomBankAccountServiceImpl;
import service.BankService;
import service.BankServiceImpl;
import service.transactionExplorer.TransactionExplorer;
import service.transactionExplorer.TransactionExplorerImpl;

@Configuration
public class AppConfig {

    @Bean(name = "bankService")
    public BankService bankService(){
        return new BankServiceImpl();
    }

    @Bean(name = "randomBankAccountService")
    public RandomBankAccountService randomBankAccountService(){
        return new RandomBankAccountServiceImpl();
    }

    @Bean(name = "transactionExplorer")
    public TransactionExplorer transactionExplorer(){
        return new TransactionExplorerImpl();
    }
}
