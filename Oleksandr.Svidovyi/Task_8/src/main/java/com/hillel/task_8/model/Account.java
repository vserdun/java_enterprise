package com.hillel.task_8.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;


@Data
public class Account {
    @PositiveOrZero
    private long balance;

    @NotBlank
    private String currency;
}
