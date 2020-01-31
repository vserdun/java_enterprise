package com.hillel.task_7.model.request_model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TransferRequestModel {
    @NotBlank
    private int recieverId;
    @NotBlank
    private long payment;
    @NotBlank
    private String currency;
}
