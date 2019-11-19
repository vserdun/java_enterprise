package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.model.User;

import java.util.List;

public interface UserRepository {

    long  addUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    User getUser(long id);

    List<User> getAllUsers();
}
