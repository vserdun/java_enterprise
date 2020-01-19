package com.hillel.bankserviceboot.service;


import com.hillel.bankserviceboot.dao.RoleRepository;
import com.hillel.bankserviceboot.dao.UserRepository;
import com.hillel.bankserviceboot.model.RoleEntity;
import com.hillel.bankserviceboot.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void addUser(UserEntity userEntity) {
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
        userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUser(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity findByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Map<String, String> getRolesMap() {
        List<RoleEntity> roles = roleRepository.findAll();

        Map<String, String> rolesMap = new HashMap<>();

        for (RoleEntity role : roles) {
            String name = role.getName();
            String text = "Role name:" + name;
            rolesMap.put(name, text);
        }
        return rolesMap;
    }
}
