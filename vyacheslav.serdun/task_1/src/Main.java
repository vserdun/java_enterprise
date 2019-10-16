import accountManager.AccountManager;
import customMap.CustomHashMap;
import customMap.CustomMap;
import pojos.Accounts;
import pojos.BankAccount;
import randomAccounts.RandomAccount;

import java.util.List;
import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) {
        CustomMap<Integer, String> map = new CustomHashMap<>(1);
        map.put(1, "Mark");
        map.put(2, "Vadim");
        map.put(3, "Richard");
        map.put(4, null);
        map.put(5, "David");
        map.put(null, "Null");
        map.put(14, "Margaret");
        map.put(26, "Donald");
        map.put(8, "Richard");

        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(4));
        System.out.println(map.get(5));
        System.out.println(map.get(14));
        System.out.println(map.get(26));
        System.out.println(map.get(8));
        System.out.println(map.get(null));

        map.put(2, "Alex");
        System.out.println(map.get(2));

        RandomAccount randomAccount = new RandomAccount();

        Accounts accounts = randomAccount.generateAccounts(5);

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
