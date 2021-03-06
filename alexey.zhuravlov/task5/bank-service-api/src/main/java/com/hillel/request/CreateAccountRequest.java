package com.hillel.request;

import com.hillel.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAccountRequest {
    private double balance;
    private User user;
}
