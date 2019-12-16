package com.hillel.bankserviceboot.model;

import lombok.Data;

@Data
public class Deposit {
    private int accountId;
    private int amount;
}
