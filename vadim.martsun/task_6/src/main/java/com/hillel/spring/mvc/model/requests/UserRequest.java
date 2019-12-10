package com.hillel.spring.mvc.model.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private int id;
    private String lastName;
    private String firstName;
    private String birthDate;
    private String gender;
}
