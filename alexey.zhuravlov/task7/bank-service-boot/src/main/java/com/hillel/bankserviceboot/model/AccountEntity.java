package com.hillel.bankserviceboot.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class AccountEntity {

    private int accountId;

    @NotBlank
    private double accountBalance;

    private int userId;

    private List<String> accStatement;



    public void addAccStatement(String string) {
        accStatement.add(string);
    }

}
