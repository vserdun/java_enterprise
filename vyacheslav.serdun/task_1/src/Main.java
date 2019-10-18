import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import java.util.*;

public class Main {



    public static void main(String args[]){
        hashMap();
        findDuplicates();
    }

    public static void hashMap() {
        System.out.println("----------HashMap------------");
        MyHashMap<BankAccount, String> hashMap = new MyHashMap();
        BankAccount key1 = new BankAccount(-1, "Name1", "Password1");
        hashMap.put(key1, "Name1");
        hashMap.put(key1, "Name2");
        System.out.println(hashMap.get(key1));
        hashMap = new MyHashMap<>();
        List<BankAccount> bankAccounts = getAccounts(30);
        for (BankAccount bankAccount: bankAccounts) {
            hashMap.put(bankAccount, bankAccount.getAcc_login_name());
        }
        for (BankAccount bankAccount: bankAccounts) {
            System.out.println(hashMap.get(bankAccount));
        }
        System.out.println(hashMap.size());
        System.out.println("-----------------------------");
    }

    public static void findDuplicates() {
        System.out.println("----------findDuplicates------------");
        int size = 200000;
        List<BankAccount> duplicates = new ArrayList<>();
        List<BankAccount> list1 = getAccounts(size);
        List<BankAccount> list2 = getAccounts(size + 1000);
        long startTime = System.currentTimeMillis();
        duplicates = findDuplicates1(list1, list2);
        System.out.println("1->" + (System.currentTimeMillis() - startTime));
        System.out.println(duplicates.size());
        startTime = System.currentTimeMillis();
        duplicates = findDuplicates2(list1, list2);
        System.out.println("2->" + (System.currentTimeMillis() - startTime));
        System.out.println(duplicates.size());
        System.out.println("-----------------------------");
    }

    public static List<BankAccount> findDuplicates1(Collection<BankAccount> collA, Collection<BankAccount> collB) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        HashSet<BankAccount> hashSet = new HashSet<>();
        for (BankAccount bankAccount : collA) {
           if (!hashSet.add(bankAccount)) {
               bankAccounts.add(bankAccount);
           }
        }
        for (BankAccount bankAccount : collB) {
            if (!hashSet.add(bankAccount)) {
                bankAccounts.add(bankAccount);
            }
        }
        return bankAccounts;
    }

    public static List<BankAccount> findDuplicates2(Collection<BankAccount> collA, Collection<BankAccount> collB) {
        Set<BankAccount> set1 = new HashSet<>(collA);
        set1.retainAll(new HashSet<>(collB));
        return new ArrayList<>(set1);
    }

    public static List<BankAccount> getAccounts(int count) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            bankAccounts.add(new BankAccount(i, "Name" + i, "Password" + i));
        }
        return bankAccounts;
    }

}
