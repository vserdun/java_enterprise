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
}

