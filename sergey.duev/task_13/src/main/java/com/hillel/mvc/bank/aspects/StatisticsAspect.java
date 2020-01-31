package com.hillel.mvc.bank.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class StatisticsAspect {

    public int usersCounter = 0;
    public int bankAccountCounter = 0;

    @AfterReturning("execution(* com.hillel.mvc.bank.dao.UserRepository.addUser(..))")
    public void addedUserStatistic() {
        usersCounter++;
        log.info("Users in system {}", usersCounter);
    }

    @AfterReturning("execution(* com.hillel.mvc.bank.dao.UserRepository.deleteUser(..))")
    public void deletedUserStatistic() {
        usersCounter--;
        log.info("Users in system {}", usersCounter);
    }

    @AfterReturning("execution(* com.hillel.mvc.bank.dao.BankAccountsRepository.addUserBankAccount(..))")
    public void addedUserBankAccountStatistic() {
        bankAccountCounter++;
        log.info("Bank accounts in system {}", bankAccountCounter);
    }

    @AfterReturning("execution(* com.hillel.mvc.bank.dao.BankAccountsRepository.deleteUserBankAccount(..))")
    public void deletedUserBankAccountStatistic() {
        bankAccountCounter--;
        log.info("Bank accounts in system {}", bankAccountCounter);
    }
}
