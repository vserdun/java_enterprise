package com.hillel.mvc.bank.controller.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.*;

/*{
    "name":"test",
    "lastName":"test2",
    "age":"19",
    "gender":"MEN",
    "email":"tera@gmail.com"
}*/

@Valid
@Data
public class UserCreate {

    @NotNull
    @NotEmpty
    @NotBlank(message = "Name must be not blank")
    private String name;
    @NotBlank(message = "Last name must be not blank")
    private String lastName;
    @Range(min = 1, message = "Age must be set")
    private int age;
    @Email(message = "Email must be valid")
    private String email;
    @NotNull(message = "Gender must be not blank")
    private String gender;
    @Valid
    @NotNull(message = "Address must be set")
    private Address address;

}
