package com.hillel.svidovyi_task4;

import com.hillel.svidovyi_task4.model.BankClient;
import com.hillel.svidovyi_task4.model.ClientRepository;
import com.hillel.svidovyi_task4.service.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
@ComponentScan("com.hillel.svidovyi_task4")
public class BankApp {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    @Qualifier("accountServiceManager")
    private ServiceManager serviceManager;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BankApp.class);
        BankApp bankApp = ctx.getBean(BankApp.class);
        bankApp.demo();
    }

    public void demo() {
        HashMap<Integer, BankClient> clients = new HashMap<>();
        clients.put(1, new BankClient(1, "Ivan", "Tolmachev"));
        clients.put(2, new BankClient(2, "Oleg", "Borenko"));
        clients.put(3, new BankClient(3, "Anna", "Rogova"));
        clients.put(4, new BankClient(4, "Olga", "Savchuk"));

        clientRepository.setClients(clients);


        serviceManager.replenish(clientRepository.getClients().get(1), 50000L);
        serviceManager.replenish(clientRepository.getClients().get(2), 70000L);
        serviceManager.replenish(clientRepository.getClients().get(3), 65000L);
        serviceManager.replenish(clientRepository.getClients().get(4), 20000L);

        serviceManager.moneyTransfer(clientRepository.getClients().get(2), clientRepository.getClients().get(4), 3500L);
        serviceManager.withdraw(clientRepository.getClients().get(1), 9990L);
        serviceManager.showBallance(clientRepository.getClients().get(1));
    }

}
