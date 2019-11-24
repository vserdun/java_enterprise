package com.hillel.spring.mvc.controllers;

import com.hillel.spring.mvc.dao.accountRepository.AccountRepository;
import com.hillel.spring.mvc.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountPageController {
    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/accountList")
    public ModelAndView getUsersList(){
        List<Account> accountList = accountRepository.getAll();

        ModelAndView modelAndView  = new ModelAndView();

        modelAndView.addObject("accounts", accountList);
        modelAndView.setViewName("accountsPage");
        return modelAndView;
    }
}
