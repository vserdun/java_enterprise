package com.hillel.mvc.bank_service.controller;

import com.hillel.mvc.bank_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;


}
