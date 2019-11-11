package com.hillel.model.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateBankAccRequest extends CreateBankAccRequest {

    private String  id;

    public UpdateBankAccRequest(String id, float balance, String userFirstName, String userLastName, String userBirthDate){
        super(balance, userFirstName, userLastName, userBirthDate);
        this.id = id;
    }
}
