package com.hillel.task_5.model;

import lombok.Data;
import lombok.NonNull;


@Data
public class Client {

    private Account account;
    @NonNull
    private Integer id;
    @NonNull
    private String name;

}
