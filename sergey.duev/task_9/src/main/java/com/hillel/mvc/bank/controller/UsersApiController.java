package com.hillel.mvc.bank.controller;

import com.hillel.mvc.bank.models.BankAccount;
import com.hillel.mvc.bank.models.UserEntity;
import com.hillel.mvc.bank.models.exceptions.BankAccountNotFoundException;
import com.hillel.mvc.bank.models.exceptions.UserNotFoundException;
import com.hillel.mvc.bank.models.exceptions.UserValidationException;
import com.hillel.mvc.bank.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return new ResponseEntity<>(bankService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(PATH_USERS + "/{" + VAR_USER_ID +"}")
    public ResponseEntity<UserEntity> getUser(@PathVariable(VAR_USER_ID) long id) {
        try {
            return new ResponseEntity<>(bankService.getUser(id), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(PATH_USERS)
    public ResponseEntity addUser(@RequestBody UserEntity user) {
        try {
            return new ResponseEntity(bankService.addUser(user), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (UserValidationException e) {
            return new ResponseEntity(e.getViolations(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(PATH_USERS + "/{" + VAR_USER_ID +"}")
    public ResponseEntity updateUser(@PathVariable(VAR_USER_ID) long userId, @RequestBody UserEntity user) {
        try {
            bankService.updateUser(userId, user);
            return new ResponseEntity<>(bankService.getUser(user.getId()), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(PATH_USERS + "/{" + VAR_USER_ID +"}")
    public ResponseEntity<List<UserEntity>> deleteUser(@PathVariable(VAR_USER_ID) long id) {
        try {
            return new ResponseEntity<>(bankService.deleteUser(id), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS)
    public ResponseEntity<List<BankAccount>> getUserBankAccounts(@PathVariable(VAR_USER_ID) long userId) {
        try {
            return new ResponseEntity<>(bankService.getUserBankAccounts(userId), HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS + "/{" + VAR_BANK_ACCOUNT_ID +"}")
    public ResponseEntity<BankAccount> getUserBankAccount(@PathVariable(VAR_USER_ID) long userId, @PathVariable(VAR_BANK_ACCOUNT_ID) long bankAccountId) {
        try {
            return new ResponseEntity<>(bankService.getUserBankAccount(userId, bankAccountId), HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (BankAccountNotFoundException banfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS)
    public ResponseEntity<List<BankAccount>> addUserBankAccount(@PathVariable(VAR_USER_ID) long userId, BankAccount bankAccount) {
        try {
            return new ResponseEntity<>(bankService.addUserBankAccount(userId, bankAccount), HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS + "/{" + VAR_BANK_ACCOUNT_ID +"}")
    public ResponseEntity<List<BankAccount>> updateUserBankAccount(@PathVariable(VAR_USER_ID) long userId, @PathVariable(VAR_BANK_ACCOUNT_ID) long bankAccountId, BankAccount bankAccount) {
        try {
            return new ResponseEntity<>(bankService.updateUserBankAccount(userId, bankAccountId, bankAccount), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (BankAccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS + "/{" + VAR_BANK_ACCOUNT_ID +"}")
    public ResponseEntity<List<BankAccount>> deleteUserBankAccount(@PathVariable(VAR_USER_ID) long userId, @PathVariable(VAR_BANK_ACCOUNT_ID) long bankAccountId) {
        try {
            return new ResponseEntity<>(bankService.deleteUserBankAccount(userId, bankAccountId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (BankAccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
