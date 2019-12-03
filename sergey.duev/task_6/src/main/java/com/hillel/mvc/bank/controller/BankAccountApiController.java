package com.hillel.mvc.bank.controller;

import com.hillel.mvc.bank.models.Statement;
import com.hillel.mvc.bank.models.exceptions.BankAccountNotFoundException;
import com.hillel.mvc.bank.models.requests.PutMoneyRequest;
import com.hillel.mvc.bank.models.requests.TransferMoneyRequest;
import com.hillel.mvc.bank.models.requests.WithdrawMoneyRequest;
import com.hillel.mvc.bank.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank/bankAccounts")
public class BankAccountApiController {

    public static final String PATH_WITHDRAW_MONEY = "/withdrawMoney";
    public static final String PATH_TRANSFER_MONEY = "/transferMoney";
    public static final String PATH_PUT_MONEY = "/putMoney";
    public static final String PATH_GET_BALANCE = "/getBalance";
    public static final String PATH_PRINT_STATEMENT= "/printStatement";
    public static final String VAR_ID = "id";

    @Autowired
    private BankService bankService;

    @PutMapping(PATH_WITHDRAW_MONEY)
    public ResponseEntity<Statement> withdrawMoney(@RequestBody WithdrawMoneyRequest withdrawRequest) {
        try {
            return new ResponseEntity(bankService.withdrawMoney(withdrawRequest.getId(), withdrawRequest.getAmount()), HttpStatus.OK);
        } catch (BankAccountNotFoundException banfe) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(PATH_TRANSFER_MONEY)
    public ResponseEntity<Statement> transferMoney(@RequestBody TransferMoneyRequest transferRequest) {
        try {
            return new ResponseEntity(bankService.transferMoney(transferRequest.getFromId(), transferRequest.getToId(), transferRequest.getAmount()), HttpStatus.OK);
        } catch (BankAccountNotFoundException bdnfe) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(PATH_PUT_MONEY)
    public ResponseEntity putMoney(@RequestBody PutMoneyRequest putMoneyRequest) {
        try {
            return new ResponseEntity(bankService.putMoney(putMoneyRequest.getId(), putMoneyRequest.getAmount()), HttpStatus.OK);
        } catch (BankAccountNotFoundException banfe) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(PATH_GET_BALANCE + "/{" + VAR_ID + "}")
    public ResponseEntity<Double> getBalance(@PathVariable long bankAccountId) {
        try {
            return new ResponseEntity(bankService.getBalance(bankAccountId), HttpStatus.OK);
        } catch (BankAccountNotFoundException banfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(PATH_PRINT_STATEMENT + "/{" + VAR_ID + "}")
    public ResponseEntity printStatement(@PathVariable long bankAccountId) {
        try {
            return new ResponseEntity(bankService.printStatement(bankAccountId), HttpStatus.OK);
        } catch (BankAccountNotFoundException banfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(PATH_PRINT_STATEMENT)
    public ResponseEntity printStatements() {
        return new ResponseEntity(bankService.printStatements(), HttpStatus.OK);
    }
}
