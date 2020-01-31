package com.hillel.bankserviceboot.service;


import com.hillel.bankserviceboot.model.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    void addUser(UserEntity userEntity);

    List<UserEntity> getUsers();

    UserEntity getUser(int id);

    void updateUser(UserEntity userEntity);

    void deleteUser(int id);

    UserEntity findByUsername(String email);

    Map<String, String> getRolesMap();
}
