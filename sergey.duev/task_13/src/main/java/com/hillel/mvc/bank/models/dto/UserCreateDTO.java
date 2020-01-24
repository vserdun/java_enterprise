package com.hillel.mvc.bank.models.dto;

import com.hillel.mvc.bank.models.Gender;
import com.hillel.mvc.bank.models.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreateDTO {

    private String name;

    private String lastName;

    private int age;

    private String email;

    private String gender;

    private AddressDTO address;
}
