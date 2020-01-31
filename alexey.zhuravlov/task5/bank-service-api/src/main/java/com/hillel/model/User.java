package com.hillel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private int id;
    private String firstName;
    private String lastName;
}
