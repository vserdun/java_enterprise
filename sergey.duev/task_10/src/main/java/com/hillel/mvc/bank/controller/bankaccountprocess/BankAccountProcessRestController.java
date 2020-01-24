package com.hillel.mvc.bank.controller.bankaccountprocess;

import com.hillel.mvc.bank.models.Statement;
import com.hillel.mvc.bank.models.dto.PutMoneyDTO;
import com.hillel.mvc.bank.models.dto.TransferMoneyDTO;
import com.hillel.mvc.bank.models.dto.WithdrawMoneyDTO;
import com.hillel.mvc.bank.services.bankaccountprocess.BankAccountProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank/bankAccounts")
public class BankAccountProcessRestController {

    public static final String PATH_WITHDRAW_MONEY = "/withdrawMoney";
    public static final String PATH_TRANSFER_MONEY = "/transferMoney";
    public static final String PATH_PUT_MONEY = "/putMoney";
    public static final String PATH_GET_BALANCE = "/getBalance";
    public static final String PATH_PRINT_STATEMENT= "/printStatement";
    public static final String VAR_ID = "id";

    @Autowired
    @Qualifier("prodBankAccountProcessService")
    private BankAccountProcessService bankAccountProcessService;

    @PutMapping(PATH_WITHDRAW_MONEY)
    public ResponseEntity<Statement> withdrawMoney(@RequestBody WithdrawMoneyDTO withdrawRequest) {
        return new ResponseEntity(bankAccountProcessService.withdrawMoney(withdrawRequest.getId(), withdrawRequest.getAmount()), HttpStatus.OK);
    }

    @PutMapping(PATH_TRANSFER_MONEY)
    public ResponseEntity<Statement> transferMoney(@RequestBody TransferMoneyDTO transferRequest) {
        return new ResponseEntity(bankAccountProcessService.transferMoney(transferRequest.getFromId(), transferRequest.getToId(), transferRequest.getAmount()), HttpStatus.OK);
    }

    @PutMapping(PATH_PUT_MONEY)
    public ResponseEntity putMoney(@RequestBody PutMoneyDTO putMoneyDTO) {
        return new ResponseEntity(bankAccountProcessService.putMoney(putMoneyDTO.getId(), putMoneyDTO.getAmount()), HttpStatus.OK);
    }

    @GetMapping(PATH_GET_BALANCE + "/{" + VAR_ID + "}")
    public ResponseEntity<Double> getBalance(@PathVariable long bankAccountId) {
        return new ResponseEntity(bankAccountProcessService.getBalance(bankAccountId), HttpStatus.OK);
    }

    @GetMapping(PATH_PRINT_STATEMENT + "/{" + VAR_ID + "}")
    public ResponseEntity printStatement(@PathVariable long bankAccountId) {
        return new ResponseEntity(bankAccountProcessService.printStatement(bankAccountId), HttpStatus.OK);
    }

    @GetMapping(PATH_PRINT_STATEMENT)
    public ResponseEntity printStatements() {
        return new ResponseEntity(bankAccountProcessService.printStatements(), HttpStatus.OK);
    }
}
