package com.hillel.mvc.bank_service.controller;

import com.hillel.mvc.bank_service.model.AccountEntity;
import com.hillel.mvc.bank_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ModelAndView getAccountsList(){
        List<AccountEntity> accountsList = accountService.getAccounts();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accounts", accountsList);
        modelAndView.setViewName("accountsList");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public String editAccountForm(Model model, @RequestParam("accountId") int accountId){
        model.addAttribute("accountAttribute", accountService.getAccount(accountId));
        return "accountEdit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editAccount")
    public String updateAccount(@ModelAttribute("accountAttribute") AccountEntity accountEntity){
        accountService.addAccount(accountEntity);
        return "redirect:/accounts/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteAccount")
    public String deleteAccount(@RequestParam("accountId") int accountId){
        accountService.deleteAccount(accountId);
        return "redirect:/accounts/list";
    }


}
