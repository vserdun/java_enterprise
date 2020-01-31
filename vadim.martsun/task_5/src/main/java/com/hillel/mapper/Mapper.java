package com.hillel.mapper;

import com.hillel.model.BankAcc;
import com.hillel.model.User;
import com.hillel.model.requests.BankAccRequest;
import com.hillel.model.requests.UserRequest;

public interface Mapper {
    BankAcc mapBankAccount(BankAccRequest request);
    User mapUser(UserRequest request);
}
