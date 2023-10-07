package io.javabrains.authentication.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Slf4j
@Configuration
public class LoggerAspect {

    @Around("execution(* io.javabrains.authentication.controller..*(..)) "
            + "|| execution(* io.javabrains.authentication.service..*(..))"
            + "|| execution(* io.javabrains.authentication.configuration..*(..))")

    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        String methodName = joinPoint.getSignature().getClass().getName();
        log.info("Execution started for method - {}",methodName);
        Object reference = joinPoint.proceed();
        log.info("Execution end for method - {}", methodName);
        return reference;
    }
}
