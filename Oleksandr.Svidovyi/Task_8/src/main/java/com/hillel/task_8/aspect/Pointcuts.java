package com.hillel.task_8.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void service() {
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repository() {
    }

    @Pointcut("execution(* com.hillel.task_8.service.*.saveClient(..))")
    public void saveClient() {
    }

    @Pointcut("execution(* com.hillel.task_8.service.*.saveAccount(..))")
    public void saveAccount() {
    }

    @Pointcut("execution(* com.hillel.task_8.service.*.removeClient(..))")
    public void deleteClient() {
    }

    @Pointcut("execution(* com.hillel.task_8.service.*.removeAccount(..))")
    public void deleteAccount() {
    }

    @Pointcut("execution(* com.hillel.task_8.controller.ClientController.*(..)) || execution(* com.hillel.task_8.controller.AccountController.*(..))")
    public void controllers() {
    }
}
