package com.hillel.mvc.springboot.model.mappers.userMapper;

import com.hillel.mvc.springboot.model.User;
import com.hillel.spring.mvc.model.requests.UserRequest;

public interface UserMapper {
    User getUser(UserRequest userRequest);
}
