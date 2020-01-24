package com.hillel.mvc.bank.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@ConditionalOnProperty(name = "bank.maintenance-mode", havingValue = "true")
@Component
@Slf4j
public class BankMaintenanceAspect {

    @Around("within(com.hillel.mvc.bank.controller.*Controller)")
    public Object bankServiceMaintenanceForJsp(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bankMaintenance");
        modelAndView.addObject("message", "Bank is on maintenance");
        return modelAndView;
    }

    @Around("within(com.hillel.mvc.bank.controller.*RestController)")
    public Object bankServiceMaintenanceForApi(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bank is on maintenance");
    }

}
