package com.hillel.mvc.bank.models.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BankAccountInfoDTO {

    private long id;

    private LocalDateTime createdAt;

    private double balance;
}
