package com.hillel.mvc.bank.controller;

import com.hillel.mvc.bank.model.BankAccount;
import com.hillel.mvc.bank.model.User;
import com.hillel.mvc.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class UsersApiController {

    public static final String PATH_USERS = "/users";
    public static final String PATH_BANK_ACCOUNTS = "/bankAccount";
    public static final String VAR_USER_ID = "userId";
    public static final String VAR_BANK_ACCOUNT_ID = "bankAccountId";

    @Autowired
    private BankService bankService;

    @GetMapping(PATH_USERS)
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(bankService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(PATH_USERS + "/{" + VAR_USER_ID +"}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return new ResponseEntity<>(bankService.getUser(id), HttpStatus.OK);
    }

    @PostMapping(PATH_USERS)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(bankService.addUser(user), HttpStatus.OK);
    }

    @PutMapping(PATH_USERS + "/{" + VAR_USER_ID +"}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        bankService.updateUser(user);
        return new ResponseEntity<>(bankService.getUser(user.getId()), HttpStatus.OK);
    }

    @DeleteMapping(PATH_USERS + "/{" + VAR_USER_ID +"}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable long id) {
        return new ResponseEntity<>(bankService.deleteUser(id), HttpStatus.OK);
    }

    @GetMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS)
    public ResponseEntity<List<BankAccount>> getUserBankAccounts(@PathVariable long userId) {
        return new ResponseEntity<>(bankService.getUserBankAccounts(userId), HttpStatus.OK);
    }

    @GetMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS + "/{" + VAR_BANK_ACCOUNT_ID +"}")
    public ResponseEntity<BankAccount> getUserBankAccount(@PathVariable(value = VAR_USER_ID) long userId, @PathVariable(value = VAR_BANK_ACCOUNT_ID) long bankAccountId) {
        return new ResponseEntity<>(bankService.getUserBankAccount(userId, bankAccountId), HttpStatus.OK);
    }

    @PostMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS)
    public ResponseEntity<List<BankAccount>> addUserBankAccount(@PathVariable long userId, BankAccount bankAccount) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PutMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS + "/{" + VAR_BANK_ACCOUNT_ID +"}")
    public ResponseEntity<List<BankAccount>> updateUserBankAccount(@PathVariable(value = VAR_USER_ID) long userId, @PathVariable(value = VAR_BANK_ACCOUNT_ID) long bankAccountId, BankAccount bankAccount) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @DeleteMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS + "/{" + VAR_BANK_ACCOUNT_ID +"}")
    public ResponseEntity<List<BankAccount>> deleteUserBankAccount(@PathVariable(value = VAR_USER_ID) long userId, @PathVariable(value = VAR_BANK_ACCOUNT_ID) long bankAccountId) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }


}
