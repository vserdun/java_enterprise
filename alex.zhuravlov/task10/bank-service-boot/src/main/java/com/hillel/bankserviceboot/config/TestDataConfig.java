package com.hillel.bankserviceboot.config;

import com.hillel.bankserviceboot.dao.UserRepository;
import com.hillel.bankserviceboot.model.AddressEntity;
import com.hillel.bankserviceboot.model.UserEntity;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TestDataConfig implements InitializingBean {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void afterPropertiesSet() throws Exception {

        List<UserEntity> usersList = Arrays.asList(
                new UserEntity("Ivan", "Petrov",
                        new AddressEntity("Kanatnaya 22", "Odessa", "65000", "state")),
                new UserEntity("Oleg", "Ivanov",
                        new AddressEntity("Kanatnaya 23", "Odessa", "65000", "state2")));

        //usersList.forEach(userEntity -> userRepository.save(userEntity));
    }
}
