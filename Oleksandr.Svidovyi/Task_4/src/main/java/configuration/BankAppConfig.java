package configuration;

import model.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import service.AccountServiceManager;
import service.ServiceManager;
import service.TestServiceManager;

@Configuration
public class BankAppConfig {

    @Bean(name = "clientRepository")
    @Scope("singleton")
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
