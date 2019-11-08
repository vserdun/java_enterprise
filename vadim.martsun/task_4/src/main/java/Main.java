import config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import objects.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import random.RandomBankAccountService;
import service.BankService;

import java.util.List;

@Slf4j
public class Main {

    public static void bankServiceDemo() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BankService bankService = (BankService) context.getBean("bankService");
        RandomBankAccountService randomBankAccountService = (RandomBankAccountService) context.getBean("randomBankAccountService");

        List<Account> accounts = randomBankAccountService.getRandomAccounts(10);
        log.info("\nGenerated accounts: ");
        accounts.forEach(System.out::println);

        bankService.topUp(accounts.get(0), 1050f);
        bankService.withdraw(accounts.get(5), 540.5f);
        bankService.transfer(accounts.get(0), accounts.get(1), 1000f);
        bankService.transfer(accounts.get(1), accounts.get(2), 560f);
        bankService.transfer(accounts.get(2), accounts.get(3), 100f);
        bankService.transfer(accounts.get(3), accounts.get(4), 457.6f);
        bankService.transfer(accounts.get(4), accounts.get(0), 88f);
    }

    public static void main(String ... args) throws Exception {
        bankServiceDemo();
    }
}
