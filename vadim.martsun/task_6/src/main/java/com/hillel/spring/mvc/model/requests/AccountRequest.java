package com.hillel.spring.mvc.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountRequest {
    private float amount;
    private String creationDate;
    private int userId;
}
