package com.hillel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String phoneNumber;
    private int age;
    private int id;
}
