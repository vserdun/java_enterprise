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
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @Min(18)
    private int age;
    @NotNull
    private LocalDate birthday;
    @NotNull
    private Gender gender;
    @Email
    private String email;
    private boolean isApprove;

}
