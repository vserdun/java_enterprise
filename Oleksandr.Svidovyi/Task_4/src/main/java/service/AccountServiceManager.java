package service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import model.BankClient;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public class AccountServiceManager implements ServiceManager {

    @Override
    public void moneyTransfer(BankClient supplier, BankClient reciever, Long payment) {
        Long supplieBallance = supplier.getBallance();
        Long recieverBallance = reciever.getBallance();

        if (supplieBallance < payment) {
            log.error("Not enough money to provide transaction!");
        } else {
            reciever.setBallance(recieverBallance + payment);
            supplier.setBallance(supplieBallance - payment);
            log.info("You have payed " + (double) payment / 100
                    + " to " + reciever.getFirstName() + " " + reciever.getSecondName()
                    + ". Current ballance: " + (double) supplier.getBallance() / 100);
        }

    }


    @Override
    public void replenish(BankClient client, Long payment) {
        Long ballance = client.getBallance();
        client.setBallance(ballance + payment);
        log.info("You have replenished your accound with " + (double) payment / 100
                + " $. Current ballance: " + (double) client.getBallance() / 100);
    }


    @Override
    public void withdraw(BankClient client, Long payment) {
        Long ballance = client.getBallance();
        if (ballance < payment) {
            log.error("Not enough money on your ballance!");
        } else {
            client.setBallance(ballance - payment);
            log.info("You have withdrawn " + (double) payment / 100
                    + " $. Current ballance: " + (double) client.getBallance() / 100);
        }
    }


    @Override

    public void showBallance(BankClient client) {
        log.info("Current ballance: " + (double) client.getBallance() / 100);
    }


    private Long getBallance(BankClient client) {
        return client.getBallance();
    }
}
