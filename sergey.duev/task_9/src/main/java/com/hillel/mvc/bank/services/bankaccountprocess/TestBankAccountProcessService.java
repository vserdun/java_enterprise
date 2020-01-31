package com.hillel.mvc.bank.services.bankaccountprocess;

import com.hillel.mvc.bank.dao.BankAccountsRepository;
import com.hillel.mvc.bank.models.Statement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestBankAccountProcessService extends ProdBankAccountProcessService {

    @Autowired
    public TestBankAccountProcessService(BankAccountsRepository bankAccountsRepository) {
        super(bankAccountsRepository);
    }

    @Override
    public Statement withdrawMoney(long bankAccount, double amount) {
       log.info("test withdrawMoney");
       return new Statement();
    }

    @Override
    public Statement transferMoney(long bankAccountFrom, long bankAccountTo, double amount) {
        log.info("test transferMoney");
        return new Statement();
    }

    @Override
    public Statement putMoney(long bankAccount, double amount) {
        log.info("test putMoney");
        return new Statement();
    }
}
