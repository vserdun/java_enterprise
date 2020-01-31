package com.hillel;

import com.hillel.model.BankAccount;
import com.hillel.model.SingleAccountOperationType;
import com.hillel.service.BankOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("com.hillel")
public class MainBankApplication {

    @Autowired
    private BankOperationService bankOperationService;

    public void bankAppDemo() {
        BankAccount olegAcc = new BankAccount("Oleg",1, 10.0f);
        BankAccount igorAcc = new BankAccount("Igor",2, 0.0f);

        bankOperationService.processSingleAccountMoneyOperation(SingleAccountOperationType.TOP_UP, olegAcc, 500f);
        bankOperationService.processTransfer(olegAcc, igorAcc, 285f);
        bankOperationService.processSingleAccountMoneyOperation(SingleAccountOperationType.WITHDRAW, igorAcc, 200);

        bankOperationService.processSingleAccountInfoOperation(SingleAccountOperationType.SHOW_STATEMENT, olegAcc);
        bankOperationService.processSingleAccountInfoOperation(SingleAccountOperationType.SHOW_STATEMENT, igorAcc);

        bankOperationService.processSingleAccountInfoOperation(SingleAccountOperationType.SHOW_BALANCE, olegAcc);
        bankOperationService.processSingleAccountInfoOperation(SingleAccountOperationType.SHOW_BALANCE, igorAcc);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainBankApplication.class);
        MainBankApplication bankOperationApplication =  ctx.getBean(MainBankApplication.class);
        bankOperationApplication.bankAppDemo();
    }
}
