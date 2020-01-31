package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        long start = System.nanoTime();


        List<BankAccount> collA = new ArrayList<>();
        List<BankAccount> collB = new ArrayList<>();

        collA.add(new BankAccount(0, "alex", "alex991"));
        collA.add(new BankAccount(1, "oleg", "alex991"));

        collB.add(new BankAccount(2, "oleg", "alex991"));
        collB.add(new BankAccount(0, "alex", "alex991"));

        for (int i = 4; i < 10001; i++) {
            collA.add(new BankAccount(i, "alex", "alex991"));
            collB.add(new BankAccount(i + 1, "andrew", "alex991"));
        }

        collA.add(new BankAccount(3, "oleg", "alex991"));

        collB.add(new BankAccount(3, "oleg", "alex991"));

        List<BankAccount> duplicates = FindDuplicates.findDuplicates3(collA, collB);
        System.out.println(duplicates);

        long finish = System.nanoTime();
        long elapsedTime = finish - start;
        double execTime = (double) elapsedTime / 1_000_000_000;
        System.out.println("Execution time: " + execTime + " seconds");

        System.out.println("Collection A size is: " + collA.size());
        System.out.println("Collection B size is: " + collB.size());
    }
}
