package com.hillel.mvc.bank.models.dto;

import lombok.Data;

@Data
public class CardCreateDTO {

    private double amount;

    private String currency;
}
