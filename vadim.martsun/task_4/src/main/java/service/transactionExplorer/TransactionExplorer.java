package service.transactionExplorer;

import objects.Account;
import objects.Transaction;

@FunctionalInterface
public interface TransactionExplorer {
    void displayTransaction(Account account, Transaction transaction);
}
