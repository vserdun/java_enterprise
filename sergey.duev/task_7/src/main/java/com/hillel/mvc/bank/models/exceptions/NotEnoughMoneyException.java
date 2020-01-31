package com.hillel.mvc.bank.models.exceptions;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException(long bankAccountId) {
        super(String.format("Not enough money on %s", bankAccountId));
    }
}
