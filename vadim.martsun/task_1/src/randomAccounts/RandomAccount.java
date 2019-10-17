package randomAccounts;

import pojos.Accounts;
import pojos.BankAccount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class RandomAccount {
    private Logger logger = Logger.getLogger(RandomAccount.class.getSimpleName());
    private static final String ALPHABET = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";

    private Random random;

    public RandomAccount() {
        random = new Random();
    }

    /**
     * Generates a String with random characters in it.
     *
     * @return a line with a random length but no more than 52 and with random
     * characters.
     */
    private String generateRandomLoginName() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < random.nextInt(ALPHABET.length()); i++) {
            result.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return result.toString();
    }

    public Accounts generateAccounts(int count) {
        if (count <= 1)
            throw new IllegalArgumentException("Count should not be less or equal to 1");

        Accounts accounts = new Accounts();
        List<BankAccount> collA = new ArrayList<>();
        List<BankAccount> collB = new ArrayList<>();

        BankAccount account;
        boolean duplicated;

        logger.info("Generating "+ count + " elements in two collections.");
        while (count > 0) {
            duplicated = random.nextBoolean();
            if (duplicated) {
                account = new BankAccount(Math.abs(random.nextInt()), generateRandomLoginName(), random.nextInt());
                collA.add(account);
                collB.add(account);
            } else {
                collA.add(new BankAccount(Math.abs(random.nextInt()), generateRandomLoginName(), random.nextInt()));
                collB.add(new BankAccount(Math.abs(random.nextInt()), generateRandomLoginName(), random.nextInt()));
            }
            count--;
        }

        logger.info("Shuffling elements.");
        Collections.shuffle(collA);
        logger.info("Elements are shuffled.");
        accounts.setAccountsA(collA);
        accounts.setAccountsB(collB);
        return accounts;
    }
}
