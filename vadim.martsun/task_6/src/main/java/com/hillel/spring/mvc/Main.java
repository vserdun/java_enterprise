package com.hillel.spring.mvc;

import com.hillel.spring.mvc.model.Account;
import com.hillel.spring.mvc.model.Gender;
import com.hillel.spring.mvc.model.User;
import com.hillel.spring.mvc.service.bankService.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import java.time.LocalDate;

@ComponentScan(value = {"com.hillel.spring.mvc.service.bankService", "com.hillel.spring.mvc.service.transactionExplorer"})
public class Main {

    @Autowired
    @Qualifier(value = "bankService")
    private BankService bankService;

    public static void main(String ... args){
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Main main = context.getBean(Main.class);

        User user = new User("Martsun", "Vadim", LocalDate.of(1997,4,6), Gender.MALE);
        Account account = new Account(1000f, user, LocalDate.now());

        main.bankService.topUp(account, 100);

        System.out.println(account);
    }
}
