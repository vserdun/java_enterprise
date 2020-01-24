package com.hillel.mvc.bank.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

/*{
    "balance":1676.0
}*/

@Data
public class BankAccountCreate {

    @Range(min = 0l, message = "Please select positive numbers Only")
    private double balance;
}
