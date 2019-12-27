package com.hillel.mvc.bank.models.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserValidationException extends Exception {

    private List<String> violations = new ArrayList<>();

    public UserValidationException(List<String> violations) {
        super();
        this.violations = violations;
    }


}
