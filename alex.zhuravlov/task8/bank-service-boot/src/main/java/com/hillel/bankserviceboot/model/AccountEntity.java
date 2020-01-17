package com.hillel.bankserviceboot.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AccountEntity {

    private int accountId;

    @NotNull
    @Min(value = 0)
    private double accountBalance;

    private int userId;

    private List<String> accStatement;


    public void addAccStatement(String string) {
        accStatement.add(string);
    }

}
