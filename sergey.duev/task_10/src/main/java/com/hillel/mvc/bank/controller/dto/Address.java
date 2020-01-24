package com.hillel.mvc.bank.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
public class Address {

    @NotBlank(message = "City must be not blank")
    private String city;
    @NotBlank(message = "Street must be not blank")
    private String street;
    @Range(min = 1, message = "House must be set")
    private int house;
    @Range(min = 1, message = "Postcode must set")
    private int posCode;
}
