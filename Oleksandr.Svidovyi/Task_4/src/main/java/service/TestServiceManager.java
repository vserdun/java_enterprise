package service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import model.BankClient;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public class TestServiceManager implements ServiceManager {
    @Override
    public void moneyTransfer(BankClient supplier, BankClient reciever, Long payment) {
        log.error("Service is in testing regime. Transactions are currently disabled. Sorry for temporary difficulties.");
    }


    @Override
    public void replenish(BankClient client, Long payment) {
        log.error("Service is in testing regime. Replenish function is currently disabled. Sorry for temporary difficulties.");
    }


    @Override
    public void withdraw(BankClient client, Long payment) {
        log.error("Service is in testing regime. Withdraw function is currently disabled. Sorry for temporary difficulties.");
    }


    @Override
    public void showBallance(BankClient client) {
        log.info("Current ballance: " + (double) client.getBallance() / 100);
    }
}
