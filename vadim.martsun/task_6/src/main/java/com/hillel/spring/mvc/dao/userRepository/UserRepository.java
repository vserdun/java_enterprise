package com.hillel.spring.mvc.dao.userRepository;

import com.hillel.spring.mvc.model.User;
import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    User getUserById(int id);
    boolean update(int id, User updatedUser);
    boolean delete(int id);
    void save(User user);
}
