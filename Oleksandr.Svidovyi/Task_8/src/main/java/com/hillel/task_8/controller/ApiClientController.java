package com.hillel.task_8.controller;

import com.hillel.task_8.model.Client;
import com.hillel.task_8.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ApiClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> getClient(@PathVariable int id) {
        Client client = clientService.getClient(id);

        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(path = "/list")
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clientsList = clientService.getClientsList();

        return new ResponseEntity<>(clientsList, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody @Validated Client client) {

        if (client != null && client.getFirstName() != null
                && client.getSecondName() != null) {

            clientService.saveClient(client);

            return new ResponseEntity<>(client, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<Client> updateClient(@RequestBody @Validated Client clientUpdateDetails, @PathVariable int id) {
        Client client = clientService.getClient(id);

        if (client != null) {

            if (clientUpdateDetails != null && clientUpdateDetails.getFirstName() != null
                    && clientUpdateDetails.getSecondName() != null) {

                client.setFirstName(clientUpdateDetails.getFirstName());
                client.setSecondName(clientUpdateDetails.getSecondName());

                return new ResponseEntity<>(client, HttpStatus.OK);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable int id) {
        Client client = clientService.getClient(id);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        clientService.removeClient(id);

        return new ResponseEntity<>("Client was deleted.", HttpStatus.OK);
    }
}
