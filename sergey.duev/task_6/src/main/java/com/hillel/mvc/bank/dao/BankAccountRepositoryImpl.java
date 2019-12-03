package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

    private Map<Long, BankAccount> bankAccountHashMap = new ConcurrentHashMap();
    private long nextId = 1L;

    @Override
    public long addBankAccount(BankAccount bankAccount) {
        long id = nextId;
        bankAccountHashMap.put(id, bankAccount);
        nextId++;
        return id;
    }

    @Override
    public void updateBankAccount(BankAccount bankAccount) {
        bankAccountHashMap.replace(bankAccount.getId(), bankAccount);
    }

    @Override
    public void deleteBankAccount(long id) {
        bankAccountHashMap.remove(id);
    }

    @Override
    public BankAccount getBankAccount(long id) {
        return bankAccountHashMap.get(id);
    }

    @Override
    public List<BankAccount> getAllBankAccounts() {
        return new ArrayList<>(bankAccountHashMap.values());
    }
}
