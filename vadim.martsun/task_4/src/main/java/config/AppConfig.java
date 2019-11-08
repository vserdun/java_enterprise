package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import random.RandomBankAccountService;
import random.RandomBankAccountServiceImpl;
import service.BankService;
import service.BankServiceImpl;

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
}
