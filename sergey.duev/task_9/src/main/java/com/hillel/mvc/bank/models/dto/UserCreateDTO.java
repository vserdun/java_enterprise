package com.hillel.mvc.bank.models.dto;

import com.hillel.mvc.bank.models.Gender;
import lombok.Data;

@Data
public class UserCreateDTO {

    private String name;

    private String lastName;

    private int age;

    private String email;

    private Gender gender;
}
