package com.hillel.bankserviceboot.controller;


import com.hillel.bankserviceboot.model.AccountEntity;
import com.hillel.bankserviceboot.model.BalanceOperation;
import com.hillel.bankserviceboot.model.BankCard;
import com.hillel.bankserviceboot.service.AccountService;
import com.hillel.bankserviceboot.service.BankService;
import com.hillel.bankserviceboot.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    @Qualifier("bankServiceImpl")
    private BankService bankService;

    @Autowired
    private CardService cardService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ModelAndView getAccountsList() {
        List<AccountEntity> accountsList = accountService.getAccounts();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accounts", accountsList);
        modelAndView.setViewName("accountsList");
        modelAndView.addObject("transfer", bankService.isTransferSupported());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deposit")
    public String depositAccountForm(Model model, @RequestParam("accountId") int accountId) {
        BalanceOperation balanceOperation = new BalanceOperation();
        balanceOperation.setAccountId(accountId);
        model.addAttribute("deposit", balanceOperation);
        return "accountDeposit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/depositAccount")
    public String depositAccount(@ModelAttribute("deposit") BalanceOperation balanceOperation) {
        bankService.deposit(balanceOperation.getAccountId(), balanceOperation.getAmount());
        return "redirect:/accounts/list";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/withdraw")
    public String withdrawAccountForm(Model model, @RequestParam("accountId") int accountId) {
        BalanceOperation balanceOperation = new BalanceOperation();
        balanceOperation.setAccountId(accountId);
        model.addAttribute("withdraw", balanceOperation);
        return "accountWithdraw";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/withdrawAccount")
    public String withdrawAccount(@ModelAttribute("withdraw") BalanceOperation balanceOperation) {
        bankService.withdraw(balanceOperation.getAccountId(), balanceOperation.getAmount());
        return "redirect:/accounts/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteAccount")
    public String deleteAccount(@RequestParam("accountId") int accountId) {
        accountService.deleteAccount(accountId);
        return "redirect:/accounts/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addCardForm")
    public String addCardForm(Model model) {
        BankCard bankCard = new BankCard();
        Map<String, String> accountsMap = accountService.getAccontsMap();
        model.addAttribute("accountsMap", accountsMap);
        model.addAttribute("bankCard", bankCard);
        return "cardAdd";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addCard")
    public String addCard(@ModelAttribute("bankCard") BankCard bankCard) {
        cardService.save(bankCard);
        return "redirect:/accounts/list";
    }


}
