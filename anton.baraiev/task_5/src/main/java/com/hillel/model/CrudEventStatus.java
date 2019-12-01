package com.hillel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrudEventStatus {
    private boolean isSuccessful;
    private String description;

}
