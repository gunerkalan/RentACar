package com.gunerk.rentacar.core.aspects;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
@RequiredArgsConstructor
public class AppLogger {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final HttpServletRequest request;

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void beans(){

    }

    @Pointcut("execution(* com.gunerk.rentacar.api.*.*(..))")
    public void controllerAccess(){

    }

    @Pointcut("execution(* com.gunerk.rentacar.service.*.*(..))")
    public void serviceAccess(){

    }

    @AfterThrowing(pointcut = "beans()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e){
        logger.error("Exception in {}.{}() with cause = {}",joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),e.getCause() != null ? e.getCause() : "NULL");
    }

    @Before(value = "controllerAccess()")
    public void logControllerAccess(JoinPoint joinPoint){
        logger.info("Accessing {}.{}() from {}",joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),request.getRemoteAddr());
    }

}
