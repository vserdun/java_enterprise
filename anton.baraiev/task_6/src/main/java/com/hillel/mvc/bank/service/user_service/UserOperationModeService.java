package com.hillel.mvc.bank.service.user_service;

import com.hillel.mvc.bank.model.User;
import java.util.List;

public interface UserOperationModeService {

    List<User> getUsers();

    User getUserById(int id);

    void addUser(User user);

    void updateUserInfo(User user);

    void deleteUser(int id);

    boolean isTestMode();

}
