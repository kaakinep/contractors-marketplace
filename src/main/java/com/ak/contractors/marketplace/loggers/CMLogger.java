package com.ak.contractors.marketplace.loggers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CMLogger {

    private final static Logger LOGGER = Logger.getLogger(CMLogger.class.getName());

    @Before("execution(* com.ak.contractors.marketplace.*.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        LOGGER.info("Entered class : " + joinPoint.getSignature().getDeclaringTypeName());
        LOGGER.info("Entered method : " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.ak.contractors.marketplace.*.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        LOGGER.info("Exiting class : " + joinPoint.getSignature().getDeclaringTypeName());
        LOGGER.info("Exiting method : " + joinPoint.getSignature().getName());
    }

    @Before("execution(* com.ak.contractors.marketplace.*.*(..))")
    public void logParentBefore(JoinPoint joinPoint) {
        LOGGER.info("Entered class : " + joinPoint.getSignature().getDeclaringTypeName());
        LOGGER.info("Entered method : " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.ak.contractors.marketplace.*.*(..))")
    public void logParentAfter(JoinPoint joinPoint) {
        LOGGER.info("Exiting class : " + joinPoint.getSignature().getDeclaringTypeName());
        LOGGER.info("Exiting method : " + joinPoint.getSignature().getName());
    }

}
