package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<BankAccount> collA = new ArrayList<>();
        collA.add(new BankAccount(0, "alex", "alex991" ));
        collA.add(new BankAccount(1, "oleg", "alex991" ));
        collA.add(new BankAccount(2, "alex", "alex888" ));

        List<BankAccount> collB = new ArrayList<>();
        collB.add(new BankAccount(3, "andrew", "alex991" ));
        collB.add(new BankAccount(1, "oleg", "alex991" ));
        collB.add(new BankAccount(4, "alex", "alex983" ));
        collB.add(new BankAccount(2, "alex", "alex888" ));


        List<BankAccount> duplicates = FindDuplicates.findDuplicates2(collA, collB);
        System.out.println(duplicates);
    }
}
