package com.hillel.bankserviceboot.model;

import lombok.Data;

@Data
public class BalanceOperation {
    private int accountId;
    private double amount;
}
