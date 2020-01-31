package com.hillel.task_9.model.request_model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountRequestModel {
    @NonNull
    private long balance;

    @NonNull
    private String currency;

//    @NonNull
//    private ClientEntity client;
}
