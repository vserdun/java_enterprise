package com.hillel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class User {

    private long id;
    @EqualsAndHashCode.Exclude
    private String name;
}
