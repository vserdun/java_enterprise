package duplicateFinder;

import java.nio.charset.Charset;
import java.util.*;

public class AccountProducer {

    public static ArrayList<BankAccount> generateAccounts(){

        //create 3 duplicates in each generated array
        ArrayList<BankAccount> accounts = new ArrayList<>();
        accounts.add(new BankAccount("111", "Petrovich", "777"));
        accounts.add(new BankAccount("222", "Fedrych", "555"));
        accounts.add(new BankAccount("333", "Ivanych", "999"));

        //generate 100_000 more random accounts
        for (int i = 0; i < 100_000; i++) {
            accounts.add(new BankAccount(generateRandomString(), generateRandomString(), generateRandomString()));
        }

        Collections.shuffle(accounts);
        return accounts;
    }

    private static String generateRandomString() {
        byte[] array = new byte[5];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}
