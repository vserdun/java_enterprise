package service;

import objects.Account;
import objects.Transaction;

import java.util.List;

public interface BankService {

    float withdraw(Account account, float amount) throws Exception;

    float topUp(Account account, float amount);

    boolean transfer(Account sender, Account receiver, float amount);

    float getAmount(Account account);

    List<Transaction> getTransactions(Account account);
}
