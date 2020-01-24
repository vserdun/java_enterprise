package com.hillel.mvc.bank.controller.user;

import com.hillel.mvc.bank.controller.dto.*;
import com.hillel.mvc.bank.models.dto.*;
import com.hillel.mvc.bank.services.UserService;
import com.hillel.mvc.bank.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/bank")
public class UsersRestController {

    public static final String PATH_USERS = "/users";
    public static final String PATH_BANK_ACCOUNTS = "/bankAccount";
    public static final String VAR_USER_ID = "userId";
    public static final String VAR_BANK_ACCOUNT_ID = "bankAccountId";

    private UserService userService;
    private Mapper mapper;

    @Autowired
    public UsersRestController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping(PATH_USERS)
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(mapper.mapList(userService.getAllUsers(), User.class), HttpStatus.OK);
    }

    @GetMapping(PATH_USERS + "/{" + VAR_USER_ID +"}")
    public ResponseEntity<User> getUser(@PathVariable(VAR_USER_ID) long id) {
        return new ResponseEntity<>(mapper.map(userService.getUser(id), User.class), HttpStatus.OK);
    }

    @PutMapping(PATH_USERS)
    public ResponseEntity<User> addUser(@Valid @RequestBody UserCreate userCreate) {
        UserCreateDTO userCreateDTO = mapper.map(userCreate, UserCreateDTO.class);
        UserDTO userDTO = userService.addUser(userCreateDTO);
        User user = mapper.map(userDTO, User.class);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping(PATH_USERS + "/{" + VAR_USER_ID +"}")
    public ResponseEntity<User> updateUser(@PathVariable(VAR_USER_ID) long userId, @Valid @RequestBody UserUpdate user) {
        userService.updateUser(userId, mapper.map(user, UserUpdateDTO.class));
        return new ResponseEntity<>(mapper.map(userService.getUser(userId), User.class), HttpStatus.OK);
    }

    @DeleteMapping(PATH_USERS + "/{" + VAR_USER_ID +"}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable(VAR_USER_ID) long id) {
        return new ResponseEntity<>(mapper.mapList(userService.deleteUser(id), User.class), HttpStatus.OK);
    }

    @GetMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS)
    public ResponseEntity<Set<BankAccount>> getUserBankAccounts(@PathVariable(VAR_USER_ID) long userId) {
        return new ResponseEntity<>(mapper.mapSet(userService.getUserBankAccounts(userId), BankAccount.class), HttpStatus.OK);
    }

    @GetMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS + "/{" + VAR_BANK_ACCOUNT_ID +"}")
    public ResponseEntity<BankAccount> getUserBankAccount(@PathVariable(VAR_USER_ID) long userId, @PathVariable(VAR_BANK_ACCOUNT_ID) long bankAccountId) {
        return new ResponseEntity<>(mapper.map(userService.getUserBankAccount(userId, bankAccountId), BankAccount.class), HttpStatus.OK);
    }

    @PutMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS)
    public ResponseEntity<Set<BankAccount>> addUserBankAccount(@PathVariable(VAR_USER_ID) long userId, @Valid @RequestBody BankAccountCreate bankAccountCreate) {
        BankAccountCreateDTO bankAccountCreateDTO = mapper.map(bankAccountCreate, BankAccountCreateDTO.class);
        Set<BankAccountDTO> bankAccountDTOs = userService.addUserBankAccount(userId, bankAccountCreateDTO);
        return new ResponseEntity<>(mapper.mapSet(bankAccountDTOs, BankAccount.class), HttpStatus.OK);
    }

    @PostMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS + "/{" + VAR_BANK_ACCOUNT_ID +"}")
    public ResponseEntity<BankAccount> updateUserBankAccount(@PathVariable(VAR_USER_ID) long userId, @PathVariable(VAR_BANK_ACCOUNT_ID) long bankAccountId, @Valid @RequestBody BankAccountUpdate bankAccount) {
        BankAccountUpdateDTO dto = mapper.map(bankAccount, BankAccountUpdateDTO.class);
        return new ResponseEntity<>(mapper.map(userService.updateUserBankAccount(userId, bankAccountId, dto), BankAccount.class), HttpStatus.OK);
    }

    @DeleteMapping(PATH_USERS + "/{" + VAR_USER_ID +"}" + PATH_BANK_ACCOUNTS + "/{" + VAR_BANK_ACCOUNT_ID +"}")
    public ResponseEntity<Set<BankAccount>> deleteUserBankAccount(@PathVariable(VAR_USER_ID) long userId, @PathVariable(VAR_BANK_ACCOUNT_ID) long bankAccountId) {
        return new ResponseEntity<>(mapper.mapSet(userService.deleteUserBankAccount(userId, bankAccountId), BankAccount.class), HttpStatus.OK);
    }
}
