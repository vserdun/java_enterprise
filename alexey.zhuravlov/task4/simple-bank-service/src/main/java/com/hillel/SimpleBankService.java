package com.hillel;

import com.hillel.model.Account;
import com.hillel.service.AccountService;
import com.hillel.service.AccountServiceImpl;
import com.hillel.service.TestAccountService;
import lombok.extern.java.Log;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Log
@Component
@ComponentScan("com.hillel")
public class SimpleBankService {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SimpleBankService.class);

        AccountServiceDemo serviceDemo = ctx.getBean(AccountServiceDemo.class);
        serviceDemo.demoRun();
    }
}


