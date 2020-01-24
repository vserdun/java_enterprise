package com.hillel.mvc.bank.controller.bankaccount;

import com.hillel.mvc.bank.controller.dto.BankAccount;
import com.hillel.mvc.bank.controller.dto.BankAccountCardAdd;
import com.hillel.mvc.bank.services.UserService;
import com.hillel.mvc.bank.utils.Mapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/bank")
public class BankAccountRestController {

    public static final String PATH_BANK_ACCOUNTS = "/bankAccount";
    public static final String PATH_CARD = "/card";
    public static final String VAR_BANK_ACCOUNT_ID = "bankAccountId";
    public static final String VAR_CARD_ID = "cardId";

    private UserService userService;
    private Mapper mapper;

    public BankAccountRestController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PutMapping(PATH_BANK_ACCOUNTS + "/{" + VAR_BANK_ACCOUNT_ID + "}" + PATH_CARD)
    public BankAccount addCard(@PathVariable(VAR_BANK_ACCOUNT_ID) long bankAccountId, @Valid @RequestBody BankAccountCardAdd bankAccountCardAdd) {
        return mapper.map(userService.addCard(bankAccountId, bankAccountCardAdd.getId()), BankAccount.class);
    }

    @DeleteMapping(PATH_BANK_ACCOUNTS + "/{" + VAR_BANK_ACCOUNT_ID + "}" + PATH_CARD + "/{" + VAR_CARD_ID + "}")
    public BankAccount removeCard(@PathVariable(VAR_BANK_ACCOUNT_ID) long bankAccountId, @PathVariable(VAR_CARD_ID) long cardId) {
        return mapper.map(userService.removeCard(bankAccountId, cardId), BankAccount.class);
    }
}
