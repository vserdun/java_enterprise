package com.hillel.mvc.bank;

import com.hillel.mvc.bank.model.User;
import com.hillel.mvc.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankApiController {

    public static final String PATH_USERS = "/users";
    public static final String PATH_BANK_ACCOUNTS = "/bankAccounts";

    public static final String VAR_ID = "id";

    @Autowired
    private BankService bankService;

    @GetMapping(PATH_USERS)
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(bankService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(PATH_USERS + "/{" + VAR_ID +"}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        return new ResponseEntity<>(bankService.getUser(id), HttpStatus.OK);
    }

    @PostMapping(PATH_USERS)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(bankService.addUser(user), HttpStatus.OK);
    }

    @PutMapping(PATH_USERS + "/{" + VAR_ID +"}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        bankService.updateUser(user);
        return new ResponseEntity<>(bankService.getUser(user.getId()), HttpStatus.OK);
    }

    @DeleteMapping(PATH_USERS + "/{" + VAR_ID +"}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable long id) {
        return new ResponseEntity(bankService.deleteUser(id), HttpStatus.OK);
    }
}
