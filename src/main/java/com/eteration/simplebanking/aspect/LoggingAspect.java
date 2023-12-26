package com.eteration.simplebanking.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @After("execution(* com.eteration.simplebanking.*.*(..))")
    public void logServiceMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Method " + methodName + " is executed.");
    }
}
