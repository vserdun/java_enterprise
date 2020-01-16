package com.hillel.mvc.bank.services.bankaccount;

import com.hillel.mvc.bank.models.BankAccount;
import com.hillel.mvc.bank.models.Statement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestBankAccountService extends ProdBankAccountService {

    @Override
    public Statement withdrawMoney(BankAccount bankAccount, double amount) {
       log.info("test withdrawMoney");
       return new Statement();
    }

    @Override
    public Statement transferMoney(BankAccount bankAccountFrom, BankAccount bankAccountTo, double amount) {
        log.info("test transferMoney");
        return new Statement();
    }

    @Override
    public Statement putMoney(BankAccount bankAccount, double amount) {
        log.info("test putMoney");
        return new Statement();
    }
}
