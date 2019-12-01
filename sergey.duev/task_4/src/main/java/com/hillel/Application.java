package com.hillel;

import com.hillel.models.Bank;
import com.hillel.models.BankDataInitializer;
import com.hillel.models.exceptions.BankAccountNotFoundException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("com.hillel");

        Bank bank = ctx.getBean(Bank.class);
        BankDataInitializer bankDataInitializer = ctx.getBean(BankDataInitializer.class);
        bank.setAccountList(bankDataInitializer.initBankAccounts());
        bank.printStatements();
        try {
            bank.withdrawMoney(1, 33333);
            bank.withdrawMoney(1, 44444);
            bank.transferMoney(1, 2, 33333);
            bank.putMoney(1, 788);
            bank.printStatement(1);
        } catch (BankAccountNotFoundException e) {
            e.printStackTrace();
        }
    }
}
