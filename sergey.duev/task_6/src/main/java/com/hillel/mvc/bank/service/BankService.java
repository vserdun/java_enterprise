package com.hillel.mvc.bank.service;

import com.hillel.mvc.bank.dao.BankAccountRepository;
import com.hillel.mvc.bank.dao.UserBankAccounts;
import com.hillel.mvc.bank.dao.UserRepository;
import com.hillel.mvc.bank.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBankAccounts userBankAccounts;

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
}
