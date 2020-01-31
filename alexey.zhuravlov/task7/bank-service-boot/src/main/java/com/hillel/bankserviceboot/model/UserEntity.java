package com.hillel.bankserviceboot.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserEntity {

    private int userId;

    @NotBlank
    private String userFirstName;

    @NotBlank
    private String userLastName;
}
