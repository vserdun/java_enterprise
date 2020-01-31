package com.hillel.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteBankAccountRequest {

    private long userId;
    private long bankAccountId;
}
