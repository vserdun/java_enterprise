package com.hillel.task_8.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
public class Client {
    private int id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String secondName;

    private List<Account> accounts;
}
