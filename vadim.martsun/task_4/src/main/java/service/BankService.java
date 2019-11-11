package service;

import objects.Account;
import objects.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BankService {

    float withdraw(Account account, float amount) throws Exception;

    float topUp(Account account, float amount);

    boolean transfer(Account sender, Account receiver, float amount);

    default float getAmount(Account account){
        return account.getAmount();
    }

    List<Transaction> getTransactions(Account account);
}
