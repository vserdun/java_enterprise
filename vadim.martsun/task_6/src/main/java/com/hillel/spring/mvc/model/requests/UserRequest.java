package com.hillel.spring.mvc.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private String lastName;
    private String firstName;
    private String birthDate;
    private String gender;
}
