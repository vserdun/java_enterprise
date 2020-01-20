package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.entities.UserEntity;

import java.util.List;

public interface UserRepository {

    long  addUser(UserEntity user);

    void updateUser(UserEntity user);

    void deleteUser(long id);

    UserEntity getUser(long id);

    List<UserEntity> getAllUsers();
}
