package com.hillel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Account {
    private int id;
    private double balance;
    private User user;
    private List<String> accStatement;

    public void addAccStatement(String string) {
        accStatement.add(string);
    }

}
