package com.hillel.task_8.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
@Aspect
@Slf4j
@ConditionalOnProperty(name = "maintenanceMode", havingValue = "true")
public class MaintenanceAspect {
    @Around("Pointcuts.controllers()")
    public ModelAndView getMaintenancePage(ProceedingJoinPoint proceedingJoinPoint) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("maintenancePage");
        return modelAndView;
    }
}
