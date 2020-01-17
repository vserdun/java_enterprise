package com.hillel.bankserviceboot.service;

import java.util.List;

public interface BankService {
    boolean withdraw(int accountId, double amount);

    boolean deposit(int accountId, double amount);

    boolean transfer(int accountId1, int accountId2, double amount);

    double getAccountEntityAmount(int accountId);

    List<String> getAccStatement(int accountId);

    boolean isTransferSupported();
}
