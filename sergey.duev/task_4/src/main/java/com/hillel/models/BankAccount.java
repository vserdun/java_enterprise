package com.hillel.models;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class BankAccount {

    @NonNull
    private long id;
    @NonNull
    private LocalDate createdAt;
    @NonNull
    private String clientName;
    private double balance;
}
