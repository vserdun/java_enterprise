package com.hillel.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@ToString
public class Statement {

    private long fromId;
    private long toId;
    private LocalDate date;
    private double amount;
}
