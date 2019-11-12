package com.hillel.mapper;

import com.hillel.model.BankAcc;
import com.hillel.model.requests.BankAccRequest;

public interface BankAccMapper {
    BankAcc map(BankAccRequest request);
}
