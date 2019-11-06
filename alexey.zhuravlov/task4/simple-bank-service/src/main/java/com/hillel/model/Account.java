package com.hillel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Account {
    private long id;
    public String owner;
    private double balance;
    private List<String> accStatement;

    public void addAccStatement(String string) {
        accStatement.add(string);
    }

}
