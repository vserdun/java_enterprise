package com.hillel.bankserviceboot.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAndStatAspect {

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

}
