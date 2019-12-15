package com.hillel.mvc.bank.service.user_service;

import com.hillel.mvc.bank.dao.UserRepository;
import com.hillel.mvc.bank.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UserOperationNormalModeService implements UserOperationModeService {

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
        userRepository.updateUserInfo(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    @Override
    public boolean isTestMode() { return false; }
}
