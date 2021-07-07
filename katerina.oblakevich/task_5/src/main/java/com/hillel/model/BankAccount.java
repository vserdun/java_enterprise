package com.hillel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@ToString
public class BankAccount {
    private long id;
    private User user;
    private long amount;
    private LocalDate creationDate;
}
