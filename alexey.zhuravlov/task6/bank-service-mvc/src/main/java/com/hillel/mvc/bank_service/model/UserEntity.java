package com.hillel.mvc.bank_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class UserEntity {
    private int userId;
    private String userFirstName;
    private String userLastName;
}
