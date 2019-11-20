package com.hillel.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BankAccount {
    private final String userName;
    private final int id;
    @NonNull
    private float moneyAmount;
    private List<String> operations = new ArrayList<>();
}
