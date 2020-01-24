package com.hillel.mvc.bank.models.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BankAccountDTO {

    private long id;

    private LocalDateTime createdAt;

    private double balance;

    private List<CardDTO> cards;
}
