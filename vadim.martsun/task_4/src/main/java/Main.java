import lombok.extern.slf4j.Slf4j;
import objects.Account;
import objects.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import random.RandomBankAccountService;
import service.BankService;
import service.transactionExplorer.TransactionExplorer;
import java.util.List;

@Slf4j
@ComponentScan({"service", "service.transactionExplorer", "random"})
public class Main {

    @Autowired
    @Qualifier(value = "bankService")
    private BankService bankService;

    @Autowired
    private RandomBankAccountService randomBankAccountService;

    @Autowired
    private TransactionExplorer transactionExplorer;

    private void bankServiceDemo() throws Exception {
        List<Account> accounts = randomBankAccountService.getRandomAccounts(10);
        log.info("\nGenerated accounts: ");
        accounts.forEach(a -> log.info(a.toString()));

        bankService.topUp(accounts.get(0), 1050f);
        bankService.withdraw(accounts.get(5), 540.5f);
        bankService.transfer(accounts.get(0), accounts.get(1), 1000f);
        bankService.transfer(accounts.get(1), accounts.get(2), 560f);
        bankService.transfer(accounts.get(2), accounts.get(3), 100f);
        bankService.transfer(accounts.get(3), accounts.get(4), 457.6f);
        bankService.transfer(accounts.get(4), accounts.get(0), 88f);

        Account account = accounts.get(0);
        List<Transaction> transactions = bankService.getTransactions(account);
        transactions.forEach(t -> transactionExplorer.displayTransaction(account,t));
    }

    public static void main(String ... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Main main = context.getBean(Main.class);
        main.bankServiceDemo();
    }
}
