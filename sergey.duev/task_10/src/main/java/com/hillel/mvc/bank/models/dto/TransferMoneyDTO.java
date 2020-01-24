package com.hillel.mvc.bank.models.dto;

import lombok.Data;

@Data
public class TransferMoneyDTO {

    private long fromId;
    private long toId;
    private double amount;
}
