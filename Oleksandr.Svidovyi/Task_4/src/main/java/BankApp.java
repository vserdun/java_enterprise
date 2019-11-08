import configuration.BankAppConfig;
import model.BankClient;
import model.ClientRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import service.ServiceManager;
import service.TestServiceManager;

import java.util.HashMap;


@Component
public class BankApp {
    private static ClientRepository clientRepository;
    private static ServiceManager serviceManager;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BankAppConfig.class);
//        serviceManager = ctx.getBean("accountServiceManager", AccountServiceManager.class);
        serviceManager = ctx.getBean("testServiceManager", TestServiceManager.class);
        clientRepository = ctx.getBean("clientRepository", ClientRepository.class);

        HashMap<Integer, BankClient> clients = new HashMap<>();
        clients.put(1, new BankClient(1, "Ivan", "Tolmachev"));
        clients.put(2, new BankClient(2, "Oleg", "Borenko"));
        clients.put(3, new BankClient(3, "Anna", "Rogova"));
        clients.put(4, new BankClient(4, "Olga", "Savchuk"));

        clientRepository.setClients(clients);

        /*
        // Использование AccountServiceManager
        serviceManager.replenish(clientRepository.getClients().get(1), 50000L);
        serviceManager.replenish(clientRepository.getClients().get(2), 70000L);
        serviceManager.replenish(clientRepository.getClients().get(3), 65000L);
        serviceManager.replenish(clientRepository.getClients().get(4), 20000L);
        serviceManager.moneyTransfer(clientRepository.getClients().get(2), clientRepository.getClients().get(4), 3500L);
        serviceManager.withdraw(clientRepository.getClients().get(1), 9990L);
        serviceManager.showBallance(clientRepository.getClients().get(1));
        */

        // Использование TestServiceManager
        serviceManager.replenish(clientRepository.getClients().get(1), 50000L);
        serviceManager.moneyTransfer(clientRepository.getClients().get(2), clientRepository.getClients().get(4), 3500L);
        serviceManager.withdraw(clientRepository.getClients().get(1), 9990L);
        serviceManager.showBallance(clientRepository.getClients().get(1));
    }

}
