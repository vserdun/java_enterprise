package com.hillel.mvc.bank.services;

import com.hillel.mvc.bank.dao.BankAccountRepository;
import com.hillel.mvc.bank.dao.UserBankAccountsRepository;
import com.hillel.mvc.bank.dao.UserRepository;
import com.hillel.mvc.bank.models.BankAccount;
import com.hillel.mvc.bank.models.Statement;
import com.hillel.mvc.bank.models.User;
import com.hillel.mvc.bank.models.exceptions.BankAccountNotFoundException;
import com.hillel.mvc.bank.services.bankaccount.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Autowired
    @Qualifier("prodBankAccountService")
    private BankAccountService bankAccountService;

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

    private BankAccount findBankAccount(long bankAccountId) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.getBankAccount(bankAccountId);
        if (bankAccount != null) {
           return bankAccount;
        }
        throw new BankAccountNotFoundException(bankAccountId);
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

    public Statement withdrawMoney(long bankAccountId, double amount) throws BankAccountNotFoundException {
        return bankAccountService.withdrawMoney(findBankAccount(bankAccountId), amount);
    }

    public Statement transferMoney(long bankAccountIdFrom, long bankAccountIdTo, double amount) throws BankAccountNotFoundException{
        return bankAccountService.transferMoney(findBankAccount(bankAccountIdFrom), findBankAccount(bankAccountIdTo), amount);
    }

    public Statement putMoney(long bankAccountId, double amount)throws BankAccountNotFoundException{
        return bankAccountService.putMoney(findBankAccount(bankAccountId), amount);
    }

    public double getBalance(long bankAccountId) throws BankAccountNotFoundException{
        return bankAccountService.getBalance(findBankAccount(bankAccountId));
    }

    public List<Statement> printStatement(long bankAccountId) throws BankAccountNotFoundException{
        return bankAccountService.printStatement(findBankAccount(bankAccountId));
    }

    public List<Statement> printStatements() {
        return bankAccountService.printStatements();
    }
}
