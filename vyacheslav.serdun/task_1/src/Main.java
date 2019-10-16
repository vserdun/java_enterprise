import accountManager.AccountManager;
import pojos.Accounts;
import pojos.BankAccount;
import randomAccounts.RandomAccount;

import java.util.List;
import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) {
        RandomAccount randomAccount = new RandomAccount();

        Accounts accounts = randomAccount.generateAccounts(100000);

        logger.info("Generated collection A: ");
        accounts.getAccountsA().forEach(System.out::println);

        logger.info("Generated collection B: ");
        accounts.getAccountsB().forEach(System.out::println);

        long start = System.currentTimeMillis();
        List<BankAccount> result = AccountManager.findDuplicates(accounts.getAccountsA(), accounts.getAccountsB());
        long finish = System.currentTimeMillis();

        long time = (finish - start);

        logger.info("Duplicates: ");
        result.forEach(System.out::println);

        logger.info("Found " + result.size() + " duplicate elements.");

        logger.info("\nTime spent: " + time + " milliseconds.");
    }
}
