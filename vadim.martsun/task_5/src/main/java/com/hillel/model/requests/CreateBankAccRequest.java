package com.hillel.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBankAccRequest {
    private float balance;
    private String userFirstName;
    private String userLastName;
    private String userBirthDate;
}
