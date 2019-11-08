import config.AppConfig;
import objects.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import random.RandomBankAccountService;
import service.BankService;

public class Main {

    public static void main(String ... args){
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BankService bankService = (BankService) context.getBean("bankService");
        RandomBankAccountService randomBankAccountService = (RandomBankAccountService) context.getBean("randomBankAccountService");

        Account account = randomBankAccountService.getRandomAccounts(1).get(0);

        System.out.println(account);
        bankService.topUp(account, 44f);

        System.out.println(account);

    }
}
