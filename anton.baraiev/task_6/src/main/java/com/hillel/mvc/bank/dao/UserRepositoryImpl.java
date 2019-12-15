package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private Map<Integer, User> users = new ConcurrentHashMap<>();
    private int currentId = 1;

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUserById(int id) {
        return users.get(id);
    }

    @Override
    public void addUser(User user) {
        if (users.get(user.getId()) == null) {
            user.setId(currentId);
            currentId++;
        }
        users.put(user.getId(), user);
    }

    @Override
    public void updateUserInfo(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void deleteUser(int id) {
        users.remove(id);
    }
}
