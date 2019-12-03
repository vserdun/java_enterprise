package com.hillel.mvc.bank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BankAccount {

    private long id;
    @NonNull
    private LocalDate createdAt;
    @NonNull
    private String clientName;
    private double balance;
}
