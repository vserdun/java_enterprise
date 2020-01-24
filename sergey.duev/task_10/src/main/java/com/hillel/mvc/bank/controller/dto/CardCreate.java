package com.hillel.mvc.bank.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
public class CardCreate {

    @Range(min = 1, message = "Amount mast be > 0")
    private double amount;
    @NotBlank(message = "Currency must be not blank")
    private String currency;
}
