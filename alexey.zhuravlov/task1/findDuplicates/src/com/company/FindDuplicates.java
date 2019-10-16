package com.company;

import java.util.*;

public class FindDuplicates
{

  public static List<BankAccount> findDuplicates1(Collection<BankAccount> collA, Collection<BankAccount> collB)
  {
    Set<BankAccount> collASet = new HashSet<>(collA);
    collASet.retainAll(collB);
    return new ArrayList<BankAccount>(collASet);
  }


  /*public static List<BankAccount> findDuplicates2(Collection<BankAccount> collA, Collection<BankAccount> collB)
  {
    List<BankAccount> duplicates = new ArrayList<>();
    Set<BankAccount> collASet = new HashSet<>(collA);
    for (BankAccount bankAccount: collB
    )
    {
      if (collASet.add(bankAccount)==false)
      {
        duplicates.add(bankAccount);
      }
    }

    return duplicates;
  }

  public static List<BankAccount> findDuplicates3(Collection<BankAccount> collA, Collection<BankAccount> collB)
  {
    List<BankAccount> duplicates = new ArrayList<>();

    Map<BankAccount, Integer> collBmap = new HashMap<>();

    for (BankAccount bankAccount : collB
    )
    {
      collBmap.put(bankAccount, 0);
    }

    for (BankAccount bankAccount : collA)
    {
      if (collBmap.containsKey(bankAccount))
      {
        duplicates.add(bankAccount);
      }

    }

    return duplicates;
  }*/

}
