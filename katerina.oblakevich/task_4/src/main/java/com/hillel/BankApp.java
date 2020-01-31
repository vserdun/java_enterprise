package com.hillel;

import com.hillel.model.BankAccount;
import com.hillel.model.User;
import com.hillel.service.BankOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@ComponentScan("com.hillel")
public class BankApp {

    @Autowired
    private BankOperationService bankOperationService;

    public void SpringDemo(LocalDate date) {
        BankAccount bankAccount1 = new BankAccount(1, new User("Oleg", "Smirnov", 30), 0, date);
        BankAccount bankAccount2 = new BankAccount(2, new User("Denis", "Virnov", 26), 3000, date);
        bankOperationService.getAccountAmount(bankAccount2);
        bankOperationService.getAccountStatement(bankAccount2);
        bankOperationService.replenishAccount(2000, bankAccount1);
        bankOperationService.getAccountAmount(bankAccount1);
        bankOperationService.transferMoneyToAnotherAccount(100, bankAccount2, bankAccount1);
        bankOperationService.getAccountAmount(bankAccount2);
        bankOperationService.withdrawMoneyFromAccount(500, bankAccount1);
        bankOperationService.getAccountAmount(bankAccount1);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BankApp.class);
        BankApp bankApp = ctx.getBean(BankApp.class);
        bankApp.SpringDemo(ctx.getBean("dateBean", LocalDate.class));
    }
}
