package com.hillel.task_8.controller;

import com.hillel.task_8.model.Account;
import com.hillel.task_8.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/clients/{id}/account")
public class AccountController {
    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ModelAndView getAccounts(@PathVariable int id) {
        List<Account> accounts = clientService.getAccountsByClientId(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clientAccounts", accounts);
        modelAndView.setViewName("clientAccountsList");

        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String addAccount(Model model) {
        model.addAttribute("clientAccountAttribute", new Account());
        return "addAccount";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/addAccount")
    public String saveAccount(@ModelAttribute("clientAccountAttribute") @Validated Account account, @PathVariable int id) {
        clientService.saveAccount(id, account);

        return "redirect:list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteAccount")
    public String deleteClient(@PathVariable int id, @ModelAttribute("accountAttribute") @Validated Account account) {
        clientService.removeAccount(id, account);

        return "redirect:list";
    }
}
