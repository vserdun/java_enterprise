package com.hillel.task_5.model.request_model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAccountRequest {
    private String clientId;
    private String currency;
    private String balance;
}
