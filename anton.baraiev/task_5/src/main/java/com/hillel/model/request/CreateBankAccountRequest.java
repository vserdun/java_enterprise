package com.hillel.model.request;

import com.hillel.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBankAccountRequest {
    private long id;
    private long amount;
    private User user;
}
