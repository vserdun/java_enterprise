package com.hillel.mvc.bank.models.dto;

import com.hillel.mvc.bank.models.Gender;
import com.hillel.mvc.bank.models.entities.Address;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private long id;

    private String name;

    private String lastName;

    private int age;

    private String email;

    private String gender;

    private List<BankAccountDTO> bankAccounts;

    private AddressDTO address;

}
