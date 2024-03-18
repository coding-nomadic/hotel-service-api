package com.hotel.service.app.aops;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
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
            logger.info("Entered method '{}' with arguments: {}", signature.getMethod().getName(), mapper.writeValueAsString(joinPoint.getArgs()));
        } catch (JsonProcessingException e) {
            logger.error("Error while converting to JSON", e);
        }
    }

    @AfterReturning(pointcut = "loggableMethods()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, ResponseEntity<?> result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        try {
            logger.info("Exited method '{}' with response: {}", signature.getMethod().getName(), mapper.writeValueAsString(result.getBody()));
        } catch (JsonProcessingException exception) {
            logger.error("Error while converting response to JSON", exception);
        }
    }

}
