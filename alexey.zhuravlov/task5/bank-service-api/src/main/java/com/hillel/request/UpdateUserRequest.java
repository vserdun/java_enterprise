package com.hillel.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserRequest {
    private int id;
    private String firstName;
    private String lastName;
}
