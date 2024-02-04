package com.example.services.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    private final ObjectMapper mapper;

    @Autowired
    public LoggingAspect(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Pointcut("within(com.example.services.controllers..*)")
    public void loggableMethods() {
    }

    @Before("loggableMethods()")
    public void logMethodEntry(JoinPoint joinPoint) throws JsonProcessingException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        try {
            log.info("Entered method '{}' with arguments: {}", signature.getMethod().getName(), mapper.writeValueAsString(joinPoint.getArgs()));
        } catch (JsonProcessingException e) {
            log.error("Error while converting to JSON", e);
        }
    }

    @AfterReturning(pointcut = "loggableMethods()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, ResponseEntity<?> result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        try {
            log.info("Exited method '{}' with response: {}", signature.getMethod().getName(), mapper.writeValueAsString(result.getBody()));
        } catch (JsonProcessingException exception) {
            log.error("Error while converting response to JSON", exception);
        }
    }

}
