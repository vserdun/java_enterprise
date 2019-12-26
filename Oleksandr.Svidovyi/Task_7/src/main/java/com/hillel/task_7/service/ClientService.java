package com.hillel.task_7.service;

import com.hillel.task_7.model.Account;
import com.hillel.task_7.model.Client;
import com.hillel.task_7.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;


    public Client getClient(int id) {
        return clientRepository.getClientById(id);
    }


    public void saveClient(Client client) {
        clientRepository.save(client);
    }


    public void removeClient(int id) {
        clientRepository.deleteClientById(id);
    }


    public List<Client> getClientsList() {
        return clientRepository.getClientsList();
    }


    public void saveAccount(int clientId, Account account) {
        Client client = clientRepository.getClientById(clientId);

        if (client.getAccounts() == null) {
            client.setAccounts(new ArrayList<Account>());
            client.getAccounts().add(account);
        } else client.getAccounts().add(account);
    }


    public void removeAccount(int id, Account account) {
        clientRepository.getClientById(id).getAccounts().remove(account);
    }


    public Account findAccountByCurrency(int clientId, String currency) {
        List<Account> accounts = clientRepository.getClientById(clientId).getAccounts();

        for (Account acc : accounts) {
            if (acc.getCurrency().equals(currency)) return acc;
        }

        return null;
    }


    public List<Account> getAccountsByClientId(int clientId) {
        return clientRepository.getClientById(clientId).getAccounts();
    }
}

