package com.hillel.bankserviceboot.service;

import com.hillel.bankserviceboot.dao.RoleRepository;
import com.hillel.bankserviceboot.dao.UserRepository;
import com.hillel.bankserviceboot.model.RoleEntity;
import com.hillel.bankserviceboot.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;



    @Before
    public void beforeAddUser(){
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        when(bCryptPasswordEncoder.encode(stringArgumentCaptor.capture())).thenReturn(new BCryptPasswordEncoder().encode("111111"));
        when(roleRepository.findByName("ROLE_USER")).thenReturn(new RoleEntity("ROLE_USER"));
    }

    @Test
    public void addUser() {
        userService.addUser(new UserEntity("Alex", "Zhuravlov", "alexzhuravlov13@gmail.com", "111111"));
        userService.addUser(new UserEntity("asdad", "asdadasd", "asdadsad@gmail.com", "asdada"));
        verify(userRepository, times(2)).save(any(UserEntity.class));
    }

    @Test
    public void getUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(new UserEntity("Alex", "Zhuravlov", "alexzhuravlov13@gmail.com", "111111")));
        List<UserEntity> users = userService.getUsers();
        assertNotNull("users should be not null", users);
        assertEquals("wrong users count",1 , users.size());
    }

    @Test
    public void getUser() {
        ArgumentCaptor<Integer> userIdArgCaptor = ArgumentCaptor.forClass(Integer.class);
        when(userRepository.findById(userIdArgCaptor.capture())).thenReturn(java.util.Optional.of(new UserEntity("Alex", "Zhuravlov", "alexzhuravlov13@gmail.com", "111111")));

        UserEntity user = userService.getUser(15);

        assertNotNull("User must be not null", user);
        assertEquals("Wrong first name value", "Alex", user.getFirstName());
        assertEquals("Wrong last name value", "Zhuravlov", user.getLastName());

    }

    @Test
    public void updateUser() {
        UserEntity alex = new UserEntity("Alex", "Zhuravlov", "alexzhuravlov13@gmail.com", "111111");
        userService.updateUser(alex);
        verify(userRepository, times(1)).save(alex);

    }

    @Test
    public void deleteUser() {
        userService.deleteUser(3);
        verify(userRepository, times(1)).deleteById(3);
    }
}