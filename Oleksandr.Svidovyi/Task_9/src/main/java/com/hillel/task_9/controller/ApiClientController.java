package com.hillel.task_9.controller;

import com.hillel.task_9.model.ClientEntity;
import com.hillel.task_9.service.AccountService;
import com.hillel.task_9.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("accountServiceImpl")
    private AccountService accountService;


    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientEntity> getClient(@PathVariable int id) {
        ClientEntity clientEntity = clientService.getClient(id);

        if (clientEntity != null) {
            return new ResponseEntity<>(clientEntity, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(path = "/list")
    public ResponseEntity<List<ClientEntity>> getClients() {
        List<ClientEntity> clientsList = clientService.getClientsList();

        return new ResponseEntity<>(clientsList, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ClientEntity> addClient(@RequestBody @Validated ClientEntity clientEntity) {

        if (clientEntity != null && clientEntity.getFirstName() != null
                && clientEntity.getLastName() != null) {

            clientService.saveClient(clientEntity);

            return new ResponseEntity<>(clientEntity, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<ClientEntity> updateClient(@RequestBody @Validated ClientEntity clientEntityUpdateDetails, @PathVariable int id) {
        ClientEntity clientEntity = clientService.getClient(id);

        if (clientEntity != null) {

            if (clientEntityUpdateDetails != null && clientEntityUpdateDetails.getLastName() != null
                    && clientEntityUpdateDetails.getEmail() != null) {

                clientEntity.setLastName(clientEntityUpdateDetails.getLastName());
                clientEntity.setEmail(clientEntityUpdateDetails.getEmail());

                clientService.saveClient(clientEntity);

                return new ResponseEntity<>(clientEntity, HttpStatus.OK);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable int id) {
        ClientEntity clientEntity = clientService.getClient(id);

        if (clientEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        clientService.removeClient(id);

        return new ResponseEntity<>("ClientEntity was deleted.", HttpStatus.OK);
    }
}
