package com.hillel.bankserviceboot.dao;


import com.hillel.bankserviceboot.model.UserEntity;

import java.util.List;

public interface UserRepository {
    List<UserEntity> getUsersList();
    UserEntity getUserEntityById(int id);
    void save(UserEntity userEntity);
    void update(UserEntity userEntity);
    void delete(int id);
}
