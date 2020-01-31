package com.hillel.mvc.bank.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Pointcut("within(com.hillel.mvc.bank.services.*)")
    public void serviceMethodsPointCut() {}

    @Before("serviceMethodsPointCut()")
    public void beforeServiceMethods(JoinPoint joinPoint) {
        log.info("Before method {} in class {}", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getSimpleName());
    }

    @After("serviceMethodsPointCut()")
    public void afterServiceMethods(JoinPoint joinPoint) {
        log.info("After method {} in class {}", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getSimpleName());
    }


    @AfterReturning(pointcut="serviceMethodsPointCut()", returning="retVal")
    public void afterReturningServiceMethods(JoinPoint joinPoint, Object retVal) {
        log.info("Method {} in class {} return value {} ", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getSimpleName(), retVal);
    }
}
