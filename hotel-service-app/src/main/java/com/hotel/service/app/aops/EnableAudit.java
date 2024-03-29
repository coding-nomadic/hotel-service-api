package com.hotel.service.app.aops;

import com.hotel.service.app.services.AuditService;
import com.hotel.service.app.utils.JsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class EnableAudit {
    private final static Logger logger = LoggerFactory.getLogger(EnableAudit.class);
    @Autowired
    private AuditService auditService;

    @Around("@annotation(EnableAuditLogs)")
    public Object auditLogExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        final Object proceed = joinPoint.proceed();
        String auditDetails = JsonUtils.toString(proceed);
        logger.info("Audit Details saved : {}",auditDetails);
        auditService.saveAuditData(auditDetails);
        return proceed;
    }
}
