package com.hillel.spring.mvc.model.mappers.userMapper;

import com.hillel.spring.mvc.model.User;
import com.hillel.spring.mvc.model.requests.UserRequest;

public interface UserMapper {
    User getUser(UserRequest userRequest);
}
