package com.hillel.mvc.bank_service.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TestBankService extends BankServiceImpl {
    @Override
    public double getAccountEntityAmount(int accountId) {
        return super.getAccountEntityAmount(accountId);
    }

    @Override
    public List<String> getAccStatement(int accountId) {
        return super.getAccStatement(accountId);
    }

    public boolean withdraw(int accountId, double amount) {
        log.info("Can't process withdraw, you are in the test mode now");
        return false;
    }

    @Override
    public boolean deposit(int accountId, double amount) {
        log.info("Can't process deposit, you are in the test mode now");
        return false;
    }

    @Override
    public boolean transfer(int accountId1, int accountId2, double amount) {
        log.info("Can't process transfer, you are in the test mode now");
        return false;
    }

    @Override
    public boolean isTransferSupported() {
        return false;
    }
}
