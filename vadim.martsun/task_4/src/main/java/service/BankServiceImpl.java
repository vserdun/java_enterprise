package service;

import lombok.extern.slf4j.Slf4j;
import objects.Account;
import objects.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BankServiceImpl implements BankService {

    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public float withdraw(Account account, float amount) throws Exception {
        float accountAmount = account.getAmount();
        if(accountAmount < amount) throw new Exception("There is not enough money on the current account");
        account.setAmount(accountAmount - amount);
        log.info("\n\n$"
                + amount
                + " has been withdrawn from an account belonged to "
                + account.getUser().getFirstName()
                + " "
                + account.getUser().getLastName()
                + "\nCurrent balance is: $"
                + account.getAmount());
        return account.getAmount();
    }

    @Override
    public float topUp(Account account, float amount) {
        account.setAmount(account.getAmount() + amount);
        log.info("\n\n$"
                + amount
                + " has been put to an account belonged to "
                + account.getUser().getFirstName()
                + " "
                + account.getUser().getLastName()
                + "\nCurrent balance is: $"
                + account.getAmount());
        return account.getAmount();
    }

    @Override
    public boolean transfer(Account sender, Account receiver, float amount){
        if(sender.getAmount() < amount) return false;

        receiver.setAmount(receiver.getAmount() + amount);
        sender.setAmount(sender.getAmount() - amount);
        transactions.add(new Transaction(LocalDate.now(), sender, receiver, amount));
        log.info("\n\n"
                + sender.getUser().getFirstName()
                + " "
                + sender.getUser().getLastName()
                + " → "
                + receiver.getUser().getFirstName()
                + " "
                + receiver.getUser().getLastName()
                + " ($" + amount + ")");
        return true;
    }

    @Override
    public float getAmount(Account account) {
        return account.getAmount();
    }

    @Override
    public List<Transaction> getTransactions(Account account) {
        return transactions.stream()
                .filter(t -> t.getReceiver().equals(account) || t.getSender().equals(account))
                .collect(Collectors.toList());
    }
}
