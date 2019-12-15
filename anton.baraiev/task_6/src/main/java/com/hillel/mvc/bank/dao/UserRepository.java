package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getUsers();

    User getUserById(int id);

    void addUser(User user);

    void updateUserInfo(User user);

    void deleteUser(int id);
}
