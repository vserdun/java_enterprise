package com.hillel.task_5.model;


import java.util.HashMap;


public class ClientRepository {
    private static ClientRepository instance = null;
    private HashMap<Integer, Client> clients;

    private ClientRepository() {
        clients = new HashMap<>();
    }

    public static ClientRepository getInstance() {
        if (instance == null) {
            instance = new ClientRepository();
        }
        return instance;
    }

    public HashMap<Integer, Client> getClients() {
        return clients;
    }

    public void save(Client client) {
        clients.put(client.getId(), client);
    }

    public Client findClientById(Integer id) {
        return clients.get(id);
    }

    public void addAccount(Integer clientId, Account account) {
        clients.get(clientId).setAccount(account);
    }
}

