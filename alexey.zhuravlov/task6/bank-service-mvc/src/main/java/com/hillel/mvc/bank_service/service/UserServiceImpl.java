package com.hillel.mvc.bank_service.service;

import com.hillel.mvc.bank_service.dao.UserRepository;
import com.hillel.mvc.bank_service.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.getUsersList();
    }

    @Override
    public UserEntity getUser(int id) {
        return userRepository.getUserEntityById(id);
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        userRepository.update(userEntity);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.delete(id);
    }
}
