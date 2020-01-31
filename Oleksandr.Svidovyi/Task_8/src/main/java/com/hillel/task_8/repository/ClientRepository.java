package com.hillel.task_8.repository;

import com.hillel.task_8.model.Client;

import java.util.List;

public interface ClientRepository {
    Client getClientById(int id);

    void save(Client client);

    void deleteClientById(int id);

    List<Client> getClientsList();
}
