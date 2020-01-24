package com.hillel.mvc.bank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Statement {

    private long fromId;
    private long toId;
    private LocalDate date;
    private double amount;
}
