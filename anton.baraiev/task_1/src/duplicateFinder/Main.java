package duplicateFinder;

import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        //Generate two 100_000 element account lists
        ArrayList<BankAccount> collA = AccountProducer.generateAccounts();
        ArrayList<BankAccount> collB = AccountProducer.generateAccounts();

        Date start = new Date();
        System.out.println(BankAccount.findDuplicates(collA, collB));
        Date end = new Date();
        System.out.println("Elapsed time " + (end.getTime() - start.getTime()) + " [ms]");

    }
}
