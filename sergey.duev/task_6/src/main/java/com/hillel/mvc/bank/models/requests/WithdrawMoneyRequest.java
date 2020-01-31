package com.hillel.mvc.bank.models.requests;

import lombok.Data;

@Data
public class WithdrawMoneyRequest {

    private long id;
    private double amount;
}
