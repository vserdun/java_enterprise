package com.hillel.mvc.bank.service;

import com.hillel.mvc.bank.dao.BankAccountRepository;
import com.hillel.mvc.bank.dao.UserBankAccountsRepository;
import com.hillel.mvc.bank.dao.UserRepository;
import com.hillel.mvc.bank.model.BankAccount;
import com.hillel.mvc.bank.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBankAccountsRepository userBankAccountsRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUser(long id) {
        return userRepository.getUser(id);
    }

    public User addUser(User user) {
        return userRepository.getUser( userRepository.addUser(user));
    }

    public List<User> deleteUser(long id) {
        userRepository.deleteUser(id);
        return userRepository.getAllUsers();
    }

    public User updateUser(User user) {
        userRepository.updateUser(user);
        return userRepository.getUser(user.getId());
    }

    public List<BankAccount> getUserBankAccounts(long userId) {
        List<BankAccount> accounts = new ArrayList<>();
        List<Long> accountsId = userBankAccountsRepository.getUserBankAccounts(userId);
        for (Long accountId : accountsId) {
            accounts.add(bankAccountRepository.getBankAccount(accountId));
        }
        return accounts;
    }

    public BankAccount getUserBankAccount(long userId, long bankAccountId) {
        List<Long> accountsId = userBankAccountsRepository.getUserBankAccounts(userId);
        if (accountsId.contains(bankAccountId)) {
            return bankAccountRepository.getBankAccount(bankAccountId);
        }
        return null;
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.getAllBankAccounts();
    }

    public List<BankAccount> addBankAccount(BankAccount bankAccount) {
        bankAccountRepository.addBankAccount(bankAccount);
        return bankAccountRepository.getAllBankAccounts();
    }

    public List<BankAccount> updateBankAccount(BankAccount bankAccount) {
        bankAccountRepository.updateBankAccount(bankAccount);
        return bankAccountRepository.getAllBankAccounts();
    }

    public List<BankAccount> deleteBankAccount(long id) {
        bankAccountRepository.deleteBankAccount(id);
        return bankAccountRepository.getAllBankAccounts();
    }

    public BankAccount getBankAccount(long id) {
        return bankAccountRepository.getBankAccount(id);
    }

    public List<BankAccount> addUserBankAccount(long id, BankAccount bankAccount) {
        if (userRepository.getUser(id) != null) {
            long bankAccountId = bankAccountRepository.addBankAccount(bankAccount);
            userBankAccountsRepository.addUserBankAccount(id, bankAccountId);
            return getUserBankAccounts(id);
        }
        return new ArrayList<>();
    }

    public List<BankAccount> updateUserBankAccount(long userId, long bankAccountId, BankAccount bankAccount) {
        if (userRepository.getUser(userId) != null) {
            if (bankAccountRepository.getBankAccount(bankAccountId) != null) {
                bankAccountRepository.updateBankAccount(bankAccount);
                return getUserBankAccounts(userId);
            }
        }
        return new ArrayList<>();
    }

    public List<BankAccount> deleteUserBankAccount(long userId, long bankAccountId) {
        if (userRepository.getUser(userId) != null) {
            if (bankAccountRepository.getBankAccount(bankAccountId) != null) {
                userBankAccountsRepository.deleteUserBankAccount(userId, bankAccountId);
                bankAccountRepository.deleteBankAccount(bankAccountId);
            }
        }
        return new ArrayList<>();
    }
}
