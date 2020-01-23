package com.hillel.task_9.controller;

import com.hillel.task_9.model.AccountEntity;
import com.hillel.task_9.model.ClientEntity;
import com.hillel.task_9.model.request_model.AccountRequestModel;
import com.hillel.task_9.model.request_model.TransferRequestModel;
import com.hillel.task_9.model.request_model.UpdateAccountRequestModel;
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
@RequestMapping("/api/clients/{id}/accounts")
public class ApiAccountController {

    @Autowired
    private ClientService clientService;

    @Autowired
    @Qualifier("accountServiceImpl")
    private AccountService accountService;


    @GetMapping(path = "/list")
    public ResponseEntity<List<AccountEntity>> getClientAccounts(@PathVariable int id) {
        ClientEntity clientEntity = clientService.getClient(id);

        if (clientEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<AccountEntity> accountEntities = clientService.getAccountsByClientId(id);

        return new ResponseEntity<>(accountEntities, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ClientEntity> addAccount(@PathVariable int id, @RequestBody @Validated AccountRequestModel accountRequestModel) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(accountRequestModel.getBalance());
        accountEntity.setCurrency(accountRequestModel.getCurrency());
        accountEntity.setClient(clientService.getClient(id));

        accountService.saveAccount(accountEntity);

        return new ResponseEntity<>(clientService.getClient(id), HttpStatus.OK);
//        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping
    public ResponseEntity<ClientEntity> deleteAccount(@PathVariable int id, @RequestBody @Validated AccountRequestModel accountRequestModel) {
        ClientEntity clientEntity = clientService.getClient(id);

        if (clientEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AccountEntity accountEntity = new AccountEntity();

        List<AccountEntity> clientAccounts = clientEntity.getAccounts();
        for (AccountEntity acc : clientAccounts) {
            if (accountRequestModel.getBalance() == acc.getBalance() && accountRequestModel.getCurrency().equals(acc.getCurrency())) {
                accountEntity = acc;
            }
        }

        accountService.removeAccount(accountEntity);

        return new ResponseEntity<>(clientService.getClient(id), HttpStatus.OK);

    }


    @PutMapping(path = "/transfer")
    public ResponseEntity<String> moneyTransfer(@PathVariable int id, @RequestBody @Validated TransferRequestModel transferModel) {
        ClientEntity supplier = clientService.getClient(id);
        ClientEntity reciever = clientService.getClient(transferModel.getRecieverId());
        long payment = transferModel.getPayment();
        String message = "";


        AccountEntity supplierAcc = clientService.findAccountByCurrency(id, transferModel.getCurrency());
        AccountEntity recieverAcc = clientService.findAccountByCurrency(reciever.getId(), transferModel.getCurrency());

        if (supplierAcc.getBalance() > payment) {
            message = accountService.moneyTransfer(supplierAcc, recieverAcc, payment);

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        message = "You don't have " + transferModel.getCurrency() + " account or enough money to provide transaction.";

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


    @PutMapping(path = "/replenish")
    public ResponseEntity<String> replenishAccount(@PathVariable int id, @RequestBody @Validated UpdateAccountRequestModel accountModel) {
        ClientEntity clientEntity = clientService.getClient(id);
        long payment = accountModel.getPayment();
        String currency = accountModel.getCurrency();

        String message = "";

        if (clientEntity != null) {
            AccountEntity accountEntity = clientService.findAccountByCurrency(id, currency);

            if (accountEntity != null) {
                message = accountService.replenish(accountEntity, payment);

                return new ResponseEntity<>(message, HttpStatus.OK);
            }

            message = "You don't have " + currency + " accountEntity.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("ClientEntity not found.", HttpStatus.NOT_FOUND);
    }


    @PutMapping(path = "/withdraw")
    public ResponseEntity<String> withdrawAccount(@PathVariable int id, @RequestBody @Validated UpdateAccountRequestModel accountModel) {
        ClientEntity clientEntity = clientService.getClient(id);
        long payment = accountModel.getPayment();
        String currency = accountModel.getCurrency();

        String message = "";

        if (clientEntity != null) {
            AccountEntity accountEntity = clientService.findAccountByCurrency(id, currency);

            if (accountEntity != null && accountEntity.getBalance() >= payment) {
                message = accountService.withdraw(accountEntity, payment);

                return new ResponseEntity<>(message, HttpStatus.OK);
            }

            message = "You don't have " + currency + " account or enough money to withdraw.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("ClientEntity not found.", HttpStatus.NOT_FOUND);
    }
}
