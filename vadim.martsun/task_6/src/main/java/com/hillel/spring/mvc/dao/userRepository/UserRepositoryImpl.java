package com.hillel.spring.mvc.dao.userRepository;

import com.hillel.spring.mvc.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private Map<Integer, User> userMap = new ConcurrentHashMap<>();
    private int userId = 0;

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User getUserById(int id) {
        return userMap.getOrDefault(id, null);
    }

    @Override
    public void save(User user) {
        userMap.put(userId, user);
        userId++;
    }
}
