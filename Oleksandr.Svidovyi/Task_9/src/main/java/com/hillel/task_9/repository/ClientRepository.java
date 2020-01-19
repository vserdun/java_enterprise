package com.hillel.task_9.repository;

import com.hillel.task_9.model.ClientEntity;

import java.util.List;

public interface ClientRepository {
    ClientEntity getClientById(int id);

    void save(ClientEntity clientEntity);

    void deleteClientById(int id);

    List<ClientEntity> getClientsList();
}
