package com.hillel.mvc.bank_service.service;

import com.hillel.mvc.bank_service.model.UserEntity;

import java.util.List;

public interface UserService {

    void addUser (UserEntity userEntity);
    List<UserEntity> getUsers();
    UserEntity getUser(int id);
    void updateUser(UserEntity userEntity);
    void deleteUser (int id);
}
