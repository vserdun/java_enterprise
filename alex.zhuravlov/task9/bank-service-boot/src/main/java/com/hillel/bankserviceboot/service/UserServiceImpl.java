package com.hillel.bankserviceboot.service;


import com.hillel.bankserviceboot.dao.UserRepository;
import com.hillel.bankserviceboot.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Transactional
    @Override
    public List<UserEntity> getUsers() {
        return userRepository.getUsersList();
    }

    @Transactional
    @Override
    public UserEntity getUser(int id) {
        return userRepository.getUserEntityById(id);
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.delete(id);
    }
}
