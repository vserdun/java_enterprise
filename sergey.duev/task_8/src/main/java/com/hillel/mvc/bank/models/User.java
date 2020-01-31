package com.hillel.mvc.bank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;
    @NotBlank(message = "Name must be not blank")
    private String name;
    @NotBlank(message = "Last name must be not blank")
    private String lastName;
    @Min(message = "Age must be more then 18", value = 18)
    private int age;
    @NotNull(message = "Gender must be not blank")
    private Gender gender;
    @Email(message = "Email must be valid")
    private String email;
    private boolean isApprove;

}
