package com.hillel.mvc.bank.controller;

import com.hillel.mvc.bank.model.BankAccount;
import com.hillel.mvc.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankAccountApiController {

    public static final String PATH_BANK_ACCOUNTS = "/bankAccounts";
    public static final String VAR_ID = "id";

    @Autowired
    private BankService bankService;

    @GetMapping(PATH_BANK_ACCOUNTS)
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        return new ResponseEntity (bankService.getAllBankAccounts(), HttpStatus.OK);
    }

    @PutMapping(PATH_BANK_ACCOUNTS + "/{" + VAR_ID +"}")
    public ResponseEntity<List<BankAccount>> addBankAccount(BankAccount bankAccount) {
        return new ResponseEntity(bankService.addBankAccount(bankAccount), HttpStatus.OK);
    }

    @PostMapping(PATH_BANK_ACCOUNTS + "/{" + VAR_ID +"}")
    public ResponseEntity<List<BankAccount>> updateBankAccount(BankAccount bankAccount) {
        return new ResponseEntity(bankService.updateBankAccount(bankAccount), HttpStatus.OK);
    }

    @DeleteMapping(PATH_BANK_ACCOUNTS + "/{" + VAR_ID +"}")
    public ResponseEntity<List<BankAccount>> deleteBankAccount(long id) {
        return new ResponseEntity(bankService.deleteBankAccount(id), HttpStatus.OK);
    }

    @GetMapping(PATH_BANK_ACCOUNTS + "/{" + VAR_ID +"}")
    public ResponseEntity<BankAccount> getBankAccount(long id) {
        return new ResponseEntity(bankService.getBankAccount(id), HttpStatus.OK);
    }
}
