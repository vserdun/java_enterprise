package com.hillel.mvc.bank_service.dao;

import com.hillel.mvc.bank_service.model.UserEntity;

import java.util.List;

public interface UserRepository {
    List<UserEntity> getUsersList();
    UserEntity getUserEntityById (int id);
    void save (UserEntity userEntity);
    void update (UserEntity userEntity);
    void delete(int id);
}
