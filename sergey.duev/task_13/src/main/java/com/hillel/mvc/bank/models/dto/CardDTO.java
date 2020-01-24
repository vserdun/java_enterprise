package com.hillel.mvc.bank.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class CardDTO {

    private long id;

    private double amount;

    private String currency;

    private List<BankAccountInfoDTO> bankAccounts;
}
