package com.hillel.spring.mvc.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private int id;
    private float amount;
    private String creationDate;
    private int userId;
}
