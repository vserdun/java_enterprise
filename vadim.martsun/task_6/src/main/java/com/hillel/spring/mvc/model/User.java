package com.hillel.spring.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private Gender gender;
}
