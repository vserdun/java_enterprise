package com.hillel.mvc.bank.models.requests;

import lombok.Data;

@Data
public class PutMoneyRequest {

    private long id;
    private double amount;
}
