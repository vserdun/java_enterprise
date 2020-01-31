package com.hillel.bankserviceboot.controller;


import com.hillel.bankserviceboot.model.AccountEntity;
import com.hillel.bankserviceboot.service.AccountService;
import com.hillel.bankserviceboot.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    @Qualifier("bankServiceImpl")
    private BankService bankService;

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
    public String depositwAccountForm(Model model, @RequestParam("accountId") int accountId) {
        model.addAttribute("accountAttribute", accountService.getAccount(accountId));
        return "accountDeposit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/depositAccount")
    public String depositAccount(@ModelAttribute("accountAttribute") @Validated AccountEntity accountEntity) {
        bankService.deposit(accountEntity.getAccountId(), accountEntity.getAccountBalance());
        return "redirect:/accounts/list";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/withdraw")
    public String withdrawAccountForm(Model model, @RequestParam("accountId") int accountId) {
        model.addAttribute("accountAttribute", accountService.getAccount(accountId));
        return "accountWithdraw";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/withdrawAccount")
    public String withdrawAccount(@ModelAttribute("accountAttribute") @Validated AccountEntity accountEntity) {
        bankService.withdraw(accountEntity.getAccountId(), accountEntity.getAccountBalance());
        return "redirect:/accounts/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteAccount")
    public String deleteAccount(@RequestParam("accountId") int accountId) {
        accountService.deleteAccount(accountId);
        return "redirect:/accounts/list";
    }


}
