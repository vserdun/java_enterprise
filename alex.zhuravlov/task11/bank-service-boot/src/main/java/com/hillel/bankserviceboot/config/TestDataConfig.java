package com.hillel.bankserviceboot.config;

import com.hillel.bankserviceboot.dao.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDataConfig implements InitializingBean {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
