package service;

import lombok.extern.slf4j.Slf4j;
import objects.Account;
import objects.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service("testService")
public class TestBankService extends AbstractBankService {

    @Override
    public float withdraw(Account account, float amount) throws Exception {
        log.info("Test mode doesn't allow withdrawing. Try again later or contact support.");
        return Float.NaN;
    }

    @Override
    public float topUp(Account account, float amount) {
        log.info("Test mode doesn't allow topping up. Try again later or contact support.");
        return Float.NaN;
    }

    @Override
    public boolean transfer(Account sender, Account receiver, float amount) {
        transactions.add(new Transaction(LocalDate.now(), sender, receiver, amount, false));
        return false;
    }
}
