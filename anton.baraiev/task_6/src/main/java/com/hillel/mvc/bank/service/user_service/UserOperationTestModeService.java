package com.hillel.mvc.bank.service.user_service;

import com.hillel.mvc.bank.dao.UserRepository;
import com.hillel.mvc.bank.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
//@Primary
public class UserOperationTestModeService implements UserOperationModeService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public void updateUserInfo(User user) {
        log.info("Changing of a user info is unavailable in the Test Mode");
    }

    @Override
    public void deleteUser(int id) {
        log.info("Deleting a new user is unavailable in the Test Mode");
    }

    @Override
    public boolean isTestMode() { return true; }
}
