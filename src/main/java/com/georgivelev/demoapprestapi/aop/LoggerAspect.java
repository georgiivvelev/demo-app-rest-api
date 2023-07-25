package com.georgivelev.demoapprestapi.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspect {

    /*
    The code bellow is for demonstration goal only.
    Better logging inside classes.
     */

    /*@Around("execution(* com.georgivelev.demoapprestapi.services.*.*(..))")
    public Object logBeforeAndAfter(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info(joinPoint.getSignature().toString() + "args: {} method started", joinPoint.getArgs());

        Object returnValue = joinPoint.proceed(joinPoint.getArgs());

        log.info(joinPoint.getSignature().toString() + "args: {} method completed with return: {}", joinPoint.getArgs(), returnValue);

        return returnValue;
    }*/

    @Around("@annotation(com.georgivelev.demoapprestapi.aop.LogCustomAnnotation)")
    public Object logRepositoryBeforeAndAfter (ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(joinPoint.getSignature().toString() + "args: {} method started", joinPoint.getArgs());

        Object returnValue = joinPoint.proceed(joinPoint.getArgs());

        log.info(joinPoint.getSignature().toString() + "args: {} method completed with return: {}", joinPoint.getArgs(), returnValue);

        return returnValue;
    }
}
