package com.hillel.bankserviceboot.config;

import com.hillel.bankserviceboot.dao.RoleRepository;
import com.hillel.bankserviceboot.dao.UserRepository;
import com.hillel.bankserviceboot.model.UserEntity;
import com.hillel.bankserviceboot.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

//@Component
public class TestDataConfig implements InitializingBean {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void afterPropertiesSet() throws Exception {
        roleRepository.createRoleIfNotFound("ROLE_ADMIN");
        roleRepository.createRoleIfNotFound("ROLE_MANAGER");
        roleRepository.createRoleIfNotFound("ROLE_USER");

        UserEntity zhuravlov = new UserEntity("Alex", "Zhuravlov", "alexzhuravlov13@gmail.com", "111111");
        userService.addUser(zhuravlov);
        zhuravlov.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName("ROLE_ADMIN"))));
        userRepository.save(zhuravlov);

        UserEntity manager = new UserEntity("manager", "manager", "manager@gmail.com", "111111");
        userService.addUser(manager);
        manager.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName("ROLE_MANAGER"))));
        userRepository.save(manager);

        UserEntity user = new UserEntity("user", "user", "user@gmail.com", "111111");
        userService.addUser(user);

    }
}
