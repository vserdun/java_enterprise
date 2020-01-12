package com.hillel.mvc.bank.services;

import com.hillel.mvc.bank.dao.UsersBankAccountsRepository;
import com.hillel.mvc.bank.dao.UserRepository;
import com.hillel.mvc.bank.models.entities.BankAccountEntity;
import com.hillel.mvc.bank.models.Statement;
import com.hillel.mvc.bank.models.entities.UserEntity;
import com.hillel.mvc.bank.models.exceptions.BankAccountNotFoundException;
import com.hillel.mvc.bank.models.exceptions.UserNotFoundException;
import com.hillel.mvc.bank.models.exceptions.UserValidationException;
import com.hillel.mvc.bank.services.bankaccount.BankAccountService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
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

    @Transactional
    public List<UserEntity> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Transactional
    public UserEntity getUser(long id) throws UserNotFoundException{
        UserEntity userEntity = userRepository.getUser(id);
        Hibernate.initialize(userEntity.getBankAccountEntityList());
        return userEntity;
    }

    @Transactional
    public UserEntity addUser(UserEntity user) throws UserNotFoundException, UserValidationException{
        throwExceptionIfUserNotValid(user);
        return userRepository.getUser( userRepository.addUser(user));
    }

    public List<UserEntity> deleteUser(long id) throws UserNotFoundException {
        userRepository.deleteUser(id);
        return userRepository.getAllUsers();
    }

    public UserEntity updateUser(long userId, UserEntity user) throws UserNotFoundException{
        userRepository.updateUser(user);
        return userRepository.getUser(userId);
    }

    @Transactional
    public Set<BankAccountEntity> getUserBankAccounts(long userId) throws UserNotFoundException {
        UserEntity userEntity = userRepository.getUser(userId);
        Hibernate.initialize(userEntity.getBankAccountEntityList());
        return userEntity.getBankAccountEntityList();
    }

    public BankAccountEntity getUserBankAccount(long userId, long bankAccountId) throws UserNotFoundException, BankAccountNotFoundException {
        return usersBankAccountsRepository.getUserBankAccount(userId, bankAccountId);
    }

    private BankAccountEntity findBankAccount(long bankAccountId) throws BankAccountNotFoundException {
        return usersBankAccountsRepository.getBankAccount(bankAccountId);
    }

    @Transactional
    public Set<BankAccountEntity> addUserBankAccount(long id, BankAccountEntity bankAccount) throws UserNotFoundException {
        UserEntity userEntity = userRepository.getUser(id);
        userEntity.addBankAccount(bankAccount);
        return userEntity.getBankAccountEntityList();
    }

    @Transactional
    public BankAccountEntity updateUserBankAccount(long userId, long bankAccountId, BankAccountEntity bankAccount) throws UserNotFoundException, BankAccountNotFoundException {
        usersBankAccountsRepository.updateUserBankAccount(bankAccount);
        return usersBankAccountsRepository.getUserBankAccount(userId, bankAccountId);
    }

    @Transactional
    public Set<BankAccountEntity> deleteUserBankAccount(long userId, long bankAccountId) throws UserNotFoundException, BankAccountNotFoundException{
        UserEntity userEntity = userRepository.getUser(userId);
        if (userEntity != null) {
            BankAccountEntity bankAccountEntity = usersBankAccountsRepository.getBankAccount(bankAccountId);
            if (bankAccountEntity != null) {
                userEntity.removeBankAccount(bankAccountEntity);
            }
            return getUserBankAccounts(userId);
        }
        return new HashSet<>();
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

    private void throwExceptionIfUserNotValid(UserEntity user) throws UserValidationException {
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            List<String> violationMsg = new ArrayList<>();
            violations.forEach(v -> {
                violationMsg.add(v.getMessage());
            });
            throw new UserValidationException(violationMsg);
        }
    }
}
