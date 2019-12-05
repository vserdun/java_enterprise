package com.hillel.mvc.bank.dao;

import com.hillel.mvc.bank.models.User;
import com.hillel.mvc.bank.models.exceptions.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private Map<Long, User> userMap = new ConcurrentHashMap<>();
    private long nextId;

    @Override
    public long addUser(User user) {
        nextId++;
        user.setId(nextId);
        userMap.put(nextId, user);
        return nextId;
    }

    @Override
    public void updateUser(long id, User user) throws UserNotFoundException {
        throwExceptionIfUserNotExists(id);
        userMap.replace(user.getId(), user);
    }

    @Override
    public void deleteUser(long id) throws UserNotFoundException {
        throwExceptionIfUserNotExists(id);
        userMap.remove(id);
    }

    @Override
    public User getUser(long id) throws UserNotFoundException {
        throwExceptionIfUserNotExists(id);
        return userMap.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    private void throwExceptionIfUserNotExists(long userId) throws UserNotFoundException {
        if (!userMap.keySet().contains(userId)) {
            throw new UserNotFoundException(userId);
        }
    }
}
