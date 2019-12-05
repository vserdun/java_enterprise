package com.hillel.mvc.bank.services;

import com.hillel.mvc.bank.dao.UsersBankAccountsRepository;
import com.hillel.mvc.bank.dao.UserRepository;
import com.hillel.mvc.bank.models.BankAccount;
import com.hillel.mvc.bank.models.Statement;
import com.hillel.mvc.bank.models.User;
import com.hillel.mvc.bank.models.exceptions.BankAccountNotFoundException;
import com.hillel.mvc.bank.models.exceptions.UserNotFoundException;
import com.hillel.mvc.bank.models.exceptions.UserValidationException;
import com.hillel.mvc.bank.services.bankaccount.BankAccountService;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BankService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UsersBankAccountsRepository usersBankAccountsRepository;
    @Autowired
    @Qualifier("prodBankAccountService")
    private BankAccountService bankAccountService;
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUser(long id) throws UserNotFoundException{
        return userRepository.getUser(id);
    }

    public User addUser(User user) throws UserNotFoundException, UserValidationException{
        throwExceptionIfUserNotValid(user);
        return userRepository.getUser( userRepository.addUser(user));
    }

    public List<User> deleteUser(long id) throws UserNotFoundException {
        userRepository.deleteUser(id);
        return userRepository.getAllUsers();
    }

    public User updateUser(long userId, User user) throws UserNotFoundException{
        userRepository.updateUser(userId, user);
        return userRepository.getUser(user.getId());
    }

    public List<BankAccount> getUserBankAccounts(long userId) throws UserNotFoundException {
        return usersBankAccountsRepository.getUserBankAccounts(userId);
    }

    public BankAccount getUserBankAccount(long userId, long bankAccountId) throws UserNotFoundException, BankAccountNotFoundException {
        return usersBankAccountsRepository.getUserBankAccount(userId, bankAccountId);
    }

    private BankAccount findBankAccount(long bankAccountId) throws BankAccountNotFoundException {
        return usersBankAccountsRepository.getBankAccount(bankAccountId);
    }

    public List<BankAccount> addUserBankAccount(long id, BankAccount bankAccount) throws UserNotFoundException {
        if (userRepository.getUser(id) != null) {
            usersBankAccountsRepository.addUserBankAccount(id, bankAccount);
            return getUserBankAccounts(id);
        }
        return new ArrayList<>();
    }

    public List<BankAccount> updateUserBankAccount(long userId, long bankAccountId, BankAccount bankAccount) throws UserNotFoundException, BankAccountNotFoundException {
        if (userRepository.getUser(userId) != null) {
            usersBankAccountsRepository.updateUserBankAccount(userId, bankAccountId, bankAccount);
            return getUserBankAccounts(userId);
        }
        return new ArrayList<>();
    }

    public List<BankAccount> deleteUserBankAccount(long userId, long bankAccountId) throws UserNotFoundException, BankAccountNotFoundException{
        if (userRepository.getUser(userId) != null) {
            usersBankAccountsRepository.deleteUserBankAccount(userId, bankAccountId);
            return getUserBankAccounts(userId);
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

    private void throwExceptionIfUserNotValid(User user) throws UserValidationException {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            List<String> violationMsg = new ArrayList<>();
            violations.forEach(v -> {
                violationMsg.add(v.getPropertyPath() + v.getMessage());
            });
            throw new UserValidationException(violationMsg);
        }
    }
}
