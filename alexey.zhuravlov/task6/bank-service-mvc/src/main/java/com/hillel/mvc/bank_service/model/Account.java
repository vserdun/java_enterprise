package com.hillel.mvc.bank_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Account {
    private int id;
    private double balance;
    private User user;
}
