package com.hillel.mvc.bank.models.dto;

import com.hillel.mvc.bank.models.Gender;
import lombok.Data;

@Data
public class UserUpdateDTO {

    private String name;

    private String lastName;

    private int age;

    private String email;

    private Gender gender;
}
