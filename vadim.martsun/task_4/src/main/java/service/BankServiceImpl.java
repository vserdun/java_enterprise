package service;

import lombok.extern.slf4j.Slf4j;
import objects.Account;
import objects.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Slf4j
@Service("bankService")
public class BankServiceImpl extends AbstractBankService {

    @Override
    public float withdraw(Account account, float amount) throws Exception {
        if(isValid(amount)){
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
        }
        return account.getAmount();
    }

    @Override
    public float topUp(Account account, float amount) {
        if (isValid(amount)) {
            account.setAmount(account.getAmount() + amount);
            log.info("\n\n$"
                    + amount
                    + " has been put to an account belonged to "
                    + account.getUser().getFirstName()
                    + " "
                    + account.getUser().getLastName()
                    + "\nCurrent balance is: $"
                    + account.getAmount());
        }
        return account.getAmount();
    }

    @Override
    public boolean transfer(Account sender, Account receiver, float amount){
        if((!isValid(amount)) || (sender.getAmount() < amount)) return false;

        receiver.setAmount(receiver.getAmount() + amount);
        sender.setAmount(sender.getAmount() - amount);
        transactions.add(new Transaction(LocalDate.now(), sender, receiver, amount, true));
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

    private boolean isValid(float amount){
        return amount > 0;
    }
}
