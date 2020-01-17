package com.hillel.mvc.bank.models.exceptions;

public class BankOnMaintenanceException extends Exception {

    public BankOnMaintenanceException() {
        super("Bank on maintenance");
    }
}
