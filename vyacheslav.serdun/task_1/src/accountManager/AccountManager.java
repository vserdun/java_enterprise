package accountManager;

import pojos.BankAccount;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AccountManager {
    public static List<BankAccount> findDuplicates(Collection<BankAccount> collA, Collection<BankAccount> collB){
        List<BankAccount> sortedCollB = new ArrayList<>(collB);
        Collections.sort(sortedCollB);

        List<BankAccount> duplicates = new ArrayList<>();
        collA.forEach(a -> {
            int index = Collections.binarySearch(sortedCollB, a, Comparator.comparingInt(BankAccount::getAccountId));
            if(index >= 0) duplicates.add(sortedCollB.get(index));
        });

        return duplicates;
    }
}
