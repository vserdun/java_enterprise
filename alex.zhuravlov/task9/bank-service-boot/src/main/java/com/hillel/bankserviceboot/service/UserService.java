package com.hillel.bankserviceboot.service;


import com.hillel.bankserviceboot.model.UserEntity;

import java.util.List;

public interface UserService {

    void addUser(UserEntity userEntity);

    List<UserEntity> getUsers();

    UserEntity getUser(int id);

    void updateUser(UserEntity userEntity);

    void deleteUser(int id);
}
