package com.hillel.task_9.controller;

import com.hillel.task_9.model.AccountEntity;
import com.hillel.task_9.service.AccountService;
import com.hillel.task_9.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("accountServiceImpl")
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ModelAndView getAccounts(@PathVariable int id) {
        List<AccountEntity> accountEntities = clientService.getAccountsByClientId(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clientAccounts", accountEntities);
        modelAndView.setViewName("clientAccountsList");

        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String addAccount(Model model) {
        model.addAttribute("clientAccountAttribute", new AccountEntity());
        return "addAccount";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/addAccount")
    public String saveAccount(@ModelAttribute("clientAccountAttribute") @Validated AccountEntity accountEntity, @PathVariable int id) {
        accountEntity.setClient(clientService.getClient(id));
        accountService.saveAccount(accountEntity);

        return "redirect:list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteAccount")
    public String deleteClient(@ModelAttribute("accountAttribute") @Validated AccountEntity accountEntity) {
        accountService.removeAccount(accountEntity);

        return "redirect:list";
    }
}
