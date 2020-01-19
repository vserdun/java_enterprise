package com.hillel.task_9.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void service() {
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repository() {
    }

    @Pointcut("execution(* com.hillel.task_9.service.*.saveClient(..))")
    public void saveClient() {
    }

    @Pointcut("execution(* com.hillel.task_9.service.*.saveAccount(..))")
    public void saveAccount() {
    }

    @Pointcut("execution(* com.hillel.task_9.service.*.removeClient(..))")
    public void deleteClient() {
    }

    @Pointcut("execution(* com.hillel.task_9.service.*.removeAccount(..))")
    public void deleteAccount() {
    }

    @Pointcut("execution(* com.hillel.task_9.controller.ClientController.*(..)) || execution(* com.hillel.task_9.controller.AccountController.*(..))")
    public void controllers() {
    }
}
