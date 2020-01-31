package com.hillel.svidovyi_task4.configuration;

import com.hillel.svidovyi_task4.model.ClientRepository;
import com.hillel.svidovyi_task4.service.AccountServiceManager;
import com.hillel.svidovyi_task4.service.ServiceManager;
import com.hillel.svidovyi_task4.service.TestServiceManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BankAppConfig {

    @Bean(name = "clientRepository")
    public ClientRepository getClientRepository() {
        return new ClientRepository();
    }


    @Bean(name = "accountServiceManager")
    public ServiceManager getAccountServiceManager() {
        return new AccountServiceManager();
    }

    @Bean(name = "testServiceManager")
    public ServiceManager getTestServiceManager() {
        return new TestServiceManager();
    }

}
