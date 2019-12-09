package com.hillel.bankserviceboot.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {

    @Pointcut("within(@org.springframework.stereotype.Service *)||bean(*Repository)")
    public void serviceOrRepository(){}
}
