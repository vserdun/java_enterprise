package com.hillel.task_7.repository;

import com.hillel.task_7.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private HashMap<Integer, Client> clients = new HashMap<>();
    private int currentId = 1;


    @Override
    public Client getClientById(int id) {
        return clients.getOrDefault(id, null);
    }


    @Override
    public List<Client> getClientsList() {
        return new ArrayList<>(clients.values());
    }

    @Override
    public void save(Client client) {
        client.setId(currentId);
        clients.put(currentId, client);
        currentId++;
    }


    @Override
    public void deleteClientById(int id) {
        clients.remove(id);
    }
}
