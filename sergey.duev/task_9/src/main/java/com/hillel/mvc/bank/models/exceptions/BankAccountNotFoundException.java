package com.hillel.mvc.bank.models.exceptions;

public class BankAccountNotFoundException extends Exception {

    public BankAccountNotFoundException(long bankAccountId) {
        super(String.format("Bank account %s not found", bankAccountId));
    }
}
