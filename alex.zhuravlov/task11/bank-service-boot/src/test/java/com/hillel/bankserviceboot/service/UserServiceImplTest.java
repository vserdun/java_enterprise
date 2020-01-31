package com.hillel.bankserviceboot.service;

import com.hillel.bankserviceboot.dao.UserRepository;
import com.hillel.bankserviceboot.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
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

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void addUser() {
        userService.addUser(new UserEntity("Alex", "Zhuravlov"));
        userService.addUser(new UserEntity("Oleg", "Olegovskiy"));
        userService.addUser(new UserEntity("Andrew", "Bernard"));
        verify(userRepository, times(3)).save(any(UserEntity.class));
    }

    @Test
    public void getUsers() {
        when(userRepository.getUsersList()).thenReturn(Arrays.asList(new UserEntity("Alex", "Zhuravlov"), new UserEntity("Oleg", "Olegovskiy")));
        List<UserEntity> users = userService.getUsers();
        assertNotNull("users should be not null", users);
        assertEquals("wrong users count",2 , users.size());
    }

    @Test
    public void getUser() {
        ArgumentCaptor<Integer> userIdArgCaptor = ArgumentCaptor.forClass(Integer.class);
        when(userRepository.getUserEntityById(userIdArgCaptor.capture())).thenReturn(new UserEntity("Alex", "Zhuravlov"));

        UserEntity user = userService.getUser(15);

        assertNotNull("User must be not null", user);
        assertEquals("Wrong first name value", "Alex", user.getUserFirstName());
        assertEquals("Wrong last name value", "Zhuravlov", user.getUserLastName());

    }

    @Test
    public void updateUser() {
        UserEntity alex = new UserEntity("Alex", "Zhuravlov");
        userService.updateUser(alex);
        verify(userRepository, times(1)).save(alex);

    }

    @Test
    public void deleteUser() {
        userService.deleteUser(1);
        verify(userRepository, times(1)).delete(1);
    }
}