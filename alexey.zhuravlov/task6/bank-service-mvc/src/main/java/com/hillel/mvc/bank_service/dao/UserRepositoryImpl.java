package com.hillel.mvc.bank_service.dao;

import com.hillel.mvc.bank_service.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private int currentUserId = 1;
    private Map<Integer, UserEntity> usersMap = new ConcurrentHashMap<>();

    @Override
    public List<UserEntity> getUsersList() {
        return new ArrayList<>(usersMap.values());
    }

    @Override
    public UserEntity getUserEntityById(int id) {
        return usersMap.get(id);
    }

    @Override
    public void save(UserEntity userEntity) {
        if (usersMap.get(userEntity.getUserId()) == null) {
            userEntity.setUserId(currentUserId);
            currentUserId++;
        }

        usersMap.put(userEntity.getUserId(), userEntity);
    }

    @Override
    public void update(UserEntity userEntity) {
        usersMap.put(userEntity.getUserId(), userEntity);
    }

    @Override
    public void delete(int id) {
    usersMap.remove(id);
    }
}
