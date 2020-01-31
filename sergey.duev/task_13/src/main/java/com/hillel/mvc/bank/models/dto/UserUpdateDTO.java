package com.hillel.mvc.bank.models.dto;

import com.hillel.mvc.bank.models.Gender;
import com.hillel.mvc.bank.models.entities.Address;
import lombok.Data;

@Data
public class UserUpdateDTO {

    private String name;

    private String lastName;

    private int age;

    private String email;

    private String gender;

    private AddressDTO address;
}
