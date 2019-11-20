package com.hillel.spring.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Account {
    private long accountId;
    private Float amount;
    private User user;
    private LocalDate creationDate;
}
