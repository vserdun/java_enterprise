package com.hillel.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private Date birthDate;
}
