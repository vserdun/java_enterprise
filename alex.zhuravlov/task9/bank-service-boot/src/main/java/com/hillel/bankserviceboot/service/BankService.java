package com.hillel.bankserviceboot.service;

public interface BankService {
    boolean withdraw(int accountId, double amount);

    boolean deposit(int accountId, double amount);

    boolean transfer(int accountId1, int accountId2, double amount);

    double getAccountEntityAmount(int accountId);

    String getAccStatement(int accountId);

    boolean isTransferSupported();
}
