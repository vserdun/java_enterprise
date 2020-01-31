package com.hillel.task_9.config;

import com.hillel.task_9.model.ClientEntity;
import com.hillel.task_9.repository.ClientRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestUsersConfig implements InitializingBean {
    @Autowired
    private ClientRepository clientRepository;


    @Override
    public void afterPropertiesSet() throws Exception {
        clientRepository.save(new ClientEntity("Ivan", "Petrov", "ivanpetrov@gmail.com"));
        clientRepository.save(new ClientEntity("Oleg", "Ivanov", "olegivanov@gmail.com"));
    }
}
