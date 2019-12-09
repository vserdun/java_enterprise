package com.hillel.bankserviceboot.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAndStatAspect {

    private int createdUsers = 0;
    private int deletedUsers = 0;

    private int createdAccounts = 0;
    private int deletedAccounts = 0;

    @Before("PointCuts.serviceOrRepository()")
    public void enteringServiceOrRepository(JoinPoint joinPoint)
    {
        log.info("Entering service or repository");
        log.info("Class name {}", joinPoint.getSignature().getDeclaringType());
        log.info("Method name {}", joinPoint.getSignature().getName());
    }

    @After("PointCuts.serviceOrRepository()")
    public void exitingServiceOrRepository(JoinPoint joinPoint)
    {
        log.info("Exiting service or repository");
        log.info("Class name {}", joinPoint.getSignature().getDeclaringType());
        log.info("Method name {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "within(@org.springframework.stereotype.Service *)||bean(*Repository)", returning = "object")
    public void serviceOrRepositoryTarget(Object object){
        if (object!=null) {
            log.info("Object {}", object);
        }
    }

    @After("execution (* com.hillel.bankserviceboot.service.*.addUser(..))")
    public void afterUserAddded(JoinPoint joinPoint){
        createdUsers++;
        log.info("Created users:{}", createdUsers);
    }

    @After("execution (* com.hillel.bankserviceboot.service.*.deleteUser(..))")
    public void afterUserDeleted(JoinPoint joinPoint){
        deletedUsers++;
        log.info("Deleted users:{}", deletedUsers);

    }

    @After("execution (* com.hillel.bankserviceboot.service.*.addAccount(..))")
    public void afterAccountAddded(JoinPoint joinPoint){
        createdAccounts++;
        log.info("Created accounts:{}", createdAccounts);
    }

    @After("execution (* com.hillel.bankserviceboot.service.*.deleteAccount(..))")
    public void afterAccountDeleted(JoinPoint joinPoint){
        deletedAccounts++;
        log.info("Deleted accounts:{}", deletedAccounts);
    }


}
