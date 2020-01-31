package com.hillel.service;

import com.hillel.model.BankAccount;
import com.hillel.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {

    private static BankService bankService;

    private Map<User, List<BankAccount>> bankAccounts = new ConcurrentHashMap<>();
    private long nextUserId = 1;
    private long nextBankAccountId = 1;

    public static BankService init() {
        if (bankService == null) {
            bankService = new BankService();
        }
        return bankService;
    }

    private BankService () {}

    public List<User> getUsers() {
        return new ArrayList<>(bankAccounts.keySet());
    }

    public List<BankAccount> getBankAccounts(User user) {
        return new ArrayList<>(bankAccounts.get(user));
    }

    public Map<User, List<BankAccount>> getAllBankAccounts() {
        return bankAccounts;
    }

    public void createNewUser(String name) {
        User user = new User(nextUserId, name);
        bankAccounts.put(user, new ArrayList<>());
        nextUserId++;
    }

    public void deleteUser(User user) {
        bankAccounts.remove(user);
    }

    public void deleteBankAccount(User user, BankAccount bankAccount) {
        bankAccounts.get(user).remove(bankAccount);
    }

    public void updateUser(User user, String name) {
        bankAccounts.keySet().forEach(user1 -> {
            if (user1.equals(user)) {
                user1.setName(name);
                return;
            }
        });
    }

    public void updateBankAccount(User user, BankAccount bankAccount, double amount) {
        bankAccounts.get(user).get(bankAccounts.get(user).indexOf(bankAccount)).setAmount(amount);
    }

    public User findUser(long id) {
        for (User user : bankAccounts.keySet()) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public BankAccount findBankAccount(long userId, long bankAccountId) {
        for (User user : bankAccounts.keySet()) {
            if (user.getId() == userId) {
                for (BankAccount bankAccount : bankAccounts.get(user)) {
                    if (bankAccount.getId() == bankAccountId) {
                        return bankAccount;
                    }
                }
            }
        }
        return null;
    }

    public void createNewBankAccount(long userId, double amount) {
        User user = findUser(userId);
        if (user != null) {
            bankAccounts.get(user).add(new BankAccount(nextBankAccountId, amount));
            nextBankAccountId++;
        }
    }

}
