package com.hillel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReqStatus {
    private boolean satus;
    private String message;
}
