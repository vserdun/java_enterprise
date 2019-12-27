package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.UserEntity;
import com.hillel.mvc.bank.models.exceptions.UserNotFoundException;

import java.util.List;

public interface UserRepository {

    long  addUser(UserEntity user);

    void updateUser(long id, UserEntity user) throws UserNotFoundException;

    void deleteUser(long id) throws UserNotFoundException;

    UserEntity getUser(long id) throws UserNotFoundException;

    List<UserEntity> getAllUsers();
}
