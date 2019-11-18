package com.hillel.svidovyi_task4.service;


import com.hillel.svidovyi_task4.model.BankClient;

public interface ServiceManager {
    void moneyTransfer(BankClient supplier, BankClient reciever, Long payment);

    void replenish(BankClient client, Long payment);

    void withdraw(BankClient client, Long payment);

    void showBallance(BankClient client);
}
