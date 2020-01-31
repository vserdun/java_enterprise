package com.hillel.spring.mvc.service.transactionExplorer;

import com.hillel.spring.mvc.model.Account;
import com.hillel.spring.mvc.model.Transaction;

@FunctionalInterface
public interface TransactionExplorer {
    void displayTransaction(Account account, Transaction transaction);
}
