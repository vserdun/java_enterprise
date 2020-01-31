package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.User;
import com.hillel.mvc.bank.models.exceptions.UserNotFoundException;

import java.util.List;

public interface UserRepository {

    long  addUser(User user);

    void updateUser(long id, User user) throws UserNotFoundException;

    void deleteUser(long id) throws UserNotFoundException;

    User getUser(long id) throws UserNotFoundException;

    List<User> getAllUsers();
}
