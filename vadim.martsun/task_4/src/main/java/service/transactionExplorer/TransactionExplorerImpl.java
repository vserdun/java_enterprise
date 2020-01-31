package service.transactionExplorer;
import lombok.extern.slf4j.Slf4j;
import objects.Account;
import objects.Transaction;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionExplorerImpl implements TransactionExplorer {
    @Override
    public void displayTransaction(Account account, Transaction transaction) {
        String senderName = transaction.getSender().getUser().getFirstName()
                + " "
                + transaction.getSender().getUser().getLastName();

        String receiverName = transaction.getReceiver().getUser().getFirstName()
                + " "
                + transaction.getReceiver().getUser().getLastName();

        String status = (transaction.isSuccessful() ? "Successful" : "Failed");
        if(transaction.getSender().equals(account)){
            log.info(senderName + " → " + receiverName + " ($" + transaction.getAmount() + ")"
                    + " " + status);
        }else if(transaction.getReceiver().equals(account)){
            log.info(receiverName + " ← " + senderName + " ($" + transaction.getAmount() + ")"
                    + " " + status);
        }
    }
}
