package com.hillel.task_6.controller;

import com.hillel.task_6.model.Account;
import com.hillel.task_6.model.Client;
import com.hillel.task_6.model.request_model.TransferRequestModel;
import com.hillel.task_6.model.request_model.UpdateAccountRequestModel;
import com.hillel.task_6.service.AccountService;
import com.hillel.task_6.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients/{id}/accounts")
public class ApiAccountController {

    @Autowired
    private ClientService clientService;

    @Autowired
    @Qualifier("testAccountServiceImpl")
    private AccountService accountService;


    @GetMapping(path = "/list")
    public ResponseEntity<List<Account>> getClientAccounts(@PathVariable int id) {
        Client client = clientService.getClient(id);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Account> accounts = clientService.getClient(id).getAccounts();

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Client> addAccount(@PathVariable int id, @RequestBody Account account) {

        if (account != null && account.getCurrency() != null
                && (Long) account.getBalance() != null) {

            clientService.saveAccount(id, account);

            return new ResponseEntity<>(clientService.getClient(id), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping
    public ResponseEntity<Client> deleteAccount(@PathVariable int id, @RequestBody Account account) {
        Client client = clientService.getClient(id);

        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (account != null && account.getCurrency() != null
                && (Long) account.getBalance() != null) {

            clientService.removeAccount(id, account);

            return new ResponseEntity<>(clientService.getClient(id), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping(path = "/transfer")
    public ResponseEntity<String> moneyTransfer(@PathVariable int id, @RequestBody TransferRequestModel transferModel) {
        Client supplier = clientService.getClient(id);
        Client reciever = clientService.getClient(transferModel.getRecieverId());
        long payment = transferModel.getPayment();
        String message = "";

        if (supplier != null && reciever != null) {

            Account supplierAcc = clientService.findAccountByCurrency(id, transferModel.getCurrency());
            Account recieverAcc = clientService.findAccountByCurrency(reciever.getId(), transferModel.getCurrency());

            if (supplierAcc != null && recieverAcc != null && supplierAcc.getBalance() > payment) {
                message = accountService.moneyTransfer(supplierAcc, recieverAcc, payment);

                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message = "You don't have " + transferModel.getCurrency() + " account or enough money to provide transaction.";

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Client not found.", HttpStatus.NOT_FOUND);
    }


    @PutMapping(path = "/replenish")
    public ResponseEntity<String> replenishAccount(@PathVariable int id, @RequestBody UpdateAccountRequestModel accountModel) {
        Client client = clientService.getClient(id);
        long payment = accountModel.getPayment();
        String currency = accountModel.getCurrency();
        Account account = clientService.findAccountByCurrency(id, currency);

        String message = "";

        if (client != null) {
            if (account != null) {
                message = accountService.replenish(account, payment);

                return new ResponseEntity<>(message, HttpStatus.OK);
            }

            message = "You don't have " + currency + " account.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Client not found.", HttpStatus.NOT_FOUND);
    }


    @PutMapping(path = "/withdraw")
    public ResponseEntity<String> withdrawAccount(@PathVariable int id, @RequestBody UpdateAccountRequestModel accountModel) {
        Client client = clientService.getClient(id);
        long payment = accountModel.getPayment();
        String currency = accountModel.getCurrency();
        Account account = clientService.findAccountByCurrency(id, currency);

        String message = "";

        if (client != null) {
            if (account != null && account.getBalance() >= payment) {
                message = accountService.withdraw(account, payment);

                return new ResponseEntity<>(message, HttpStatus.OK);
            }

            message = "You don't have " + currency + " account or enough money to withdraw.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Client not found.", HttpStatus.NOT_FOUND);
    }
}
