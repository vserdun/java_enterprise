package com.hillel.mvc.bank.controller;

import com.hillel.mvc.bank.model.BankAccount;
import com.hillel.mvc.bank.model.User;
import com.hillel.mvc.bank.service.account_service.AccountOperationModeService;
import com.hillel.mvc.bank.service.user_service.UserOperationModeService;
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
public class BankAccountController {

    @Autowired
    private UserOperationModeService userService;

    @Autowired
    private AccountOperationModeService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ModelAndView getAccountList() {
        ModelAndView modelAndView = new ModelAndView();
        List<BankAccount> allAccounts = accountService.getAccounts();
        modelAndView.addObject("accounts", allAccounts);
        modelAndView.setViewName("accounts");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public String editAccount(Model model, @RequestParam("id") long id) {
        model.addAttribute("accountAttribute", accountService.getAccountById(id));
        return "editAccount";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editAccount")
    public String updateAccount(@ModelAttribute("accountAttribute") BankAccount account) {
        accountService.updateAccountInfo(account);
        return "redirect:/accounts/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteAccount")
    public String deleteAccount(@RequestParam("id") long id) {
        for (User user : userService.getUsers()) {
            if (user.getAccount().getId() == id) {
                user.setAccount(null);
            }
        }
        accountService.deleteAccount(id);
        return "redirect:/accounts/list";
    }
}
