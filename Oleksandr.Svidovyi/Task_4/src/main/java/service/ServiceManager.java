package service;


import model.BankClient;

public interface ServiceManager {
    void moneyTransfer(BankClient supplier, BankClient reciever, Long payment);

    void replenish(BankClient client, Long payment);

    void withdraw(BankClient client, Long payment);

    void showBallance(BankClient client);
}
