package com.hillel.task_5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer clientId;
    private String currency;
    private Long balance;

}
