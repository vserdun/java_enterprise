package com.hillel.mvc.bank.models.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private String city;

    private String street;

    private int house;

    private int posCode;
}
