package com.hillel.models.exceptions;

import java.time.LocalDateTime;

public class BankAccountNotFoundException extends Exception {

    public BankAccountNotFoundException(long bankAccountId) {
        super(String.format("Bank account %s not found", bankAccountId));
    }
}
