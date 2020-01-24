package com.hillel.mvc.bank.models.dto;

import lombok.Data;

@Data
public class PutMoneyDTO {

    private long id;
    private double amount;
}
