package com.hillel.mvc.bank.models.requests;

import lombok.Data;

@Data
public class TransferMoneyRequest {

    private long fromId;
    private long toId;
    private double amount;
}
