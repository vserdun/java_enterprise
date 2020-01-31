package com.hillel.task_8.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    private int createdClients = 0;
    private int createdAccounts = 0;
    private int deletedClients = 0;
    private int deletedAccounts = 0;

    @Before("Pointcuts.service()")
    public void serviceEntry(JoinPoint joinPoint) {
        log.info("Entry into Service class");
        log.info("Class name - [{}]", joinPoint.getSignature().getDeclaringType());
        log.info("Method invoked - [{}]", joinPoint.getSignature().getName());
    }


    @Before("Pointcuts.repository()")
    public void repositoryEntry(JoinPoint joinPoint) {
        log.info("Entry into Repository class");
        log.info("Class name - [{}]", joinPoint.getSignature().getDeclaringType());
        log.info("Method invoked - [{}]", joinPoint.getSignature().getName());
    }


    @After("Pointcuts.service()")
    public void serviceExit(JoinPoint joinPoint) {
        log.info("Exit from Service class");
        log.info("Class name - [{}]", joinPoint.getSignature().getDeclaringType());
        log.info("Method invoked - [{}]", joinPoint.getSignature().getName());
    }


    @After("Pointcuts.repository()")
    public void repositoryExit(JoinPoint joinPoint) {
        log.info("Exit from Repository class");
        log.info("Class name - [{}]", joinPoint.getSignature().getDeclaringType());
        log.info("Method invoked - [{}]", joinPoint.getSignature().getName());
    }


    @AfterReturning(pointcut = "execution(* com.hillel.task_8.service.*.*(..))", returning = "object")
    public void serviceReturningLog(Object object) {
        if (object != null) log.info("Service returned [{}]", object.toString());
    }


    @AfterReturning(pointcut = "execution(* com.hillel.task_8.repository.*.*(..))", returning = "object")
    public void repositoryeturningLog(Object object) {
        if (object != null) log.info("Repository returned [{}]", object.toString());
    }


    @After("Pointcuts.saveClient()")
    public void createdClientNumber() {
        createdClients++;
        log.info("{} clients were created", createdClients);
    }


    @After("Pointcuts.saveAccount()")
    public void createdAccountNumber() {
        createdAccounts++;
        log.info("{} accounts were created", createdAccounts);
    }


    @After("Pointcuts.deleteClient()")
    public void deletedClientNumber() {
        deletedClients++;
        log.info("{} clients were deleted", deletedClients);
    }


    @After("Pointcuts.deleteAccount()")
    public void deletedAccountNumber() {
        deletedAccounts++;
        log.info("{} accounts were deleted", deletedAccounts);
    }
}
