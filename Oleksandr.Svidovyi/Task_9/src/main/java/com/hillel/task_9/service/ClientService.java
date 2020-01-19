package com.hillel.task_9.service;

import com.hillel.task_9.model.AccountEntity;
import com.hillel.task_9.model.ClientEntity;
import com.hillel.task_9.repository.AccountRepository;
import com.hillel.task_9.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;


    public ClientEntity getClient(int id) {
        return clientRepository.getClientById(id);
    }


    public void saveClient(ClientEntity clientEntity) {
        clientRepository.save(clientEntity);
    }


    public void removeClient(int id) {
        clientRepository.deleteClientById(id);
    }


    public List<ClientEntity> getClientsList() {
        return clientRepository.getClientsList();
    }


    public void saveAccount(int clientId, AccountEntity accountEntity) {
        ClientEntity clientEntity = clientRepository.getClientById(clientId);

        if (clientEntity.getAccounts() == null) {
            clientEntity.setAccounts(new ArrayList<AccountEntity>());
            clientEntity.getAccounts().add(accountEntity);
        } else clientEntity.getAccounts().add(accountEntity);
    }


    public AccountEntity findAccountByCurrency(int clientId, String currency) {
        ClientEntity clientEntity = clientRepository.getClientById(clientId);

        if (clientEntity != null) {
            List<AccountEntity> accountEntities = accountRepository.getAccountsByClientId(clientId);

            for (AccountEntity acc : accountEntities) {
                if (acc.getCurrency().equals(currency)) return acc;
            }
        }

        return null;
    }


    public List<AccountEntity> getAccountsByClientId(int clientId) {
        return clientRepository.getClientById(clientId).getAccounts();
    }
}

