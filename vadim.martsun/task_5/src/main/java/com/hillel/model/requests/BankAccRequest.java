package com.hillel.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankAccRequest {
    private float balance;
    private long userId;
}
