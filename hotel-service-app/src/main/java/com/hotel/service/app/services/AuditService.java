package com.hotel.service.app.services;

import com.hotel.service.app.entities.Audit;
import com.hotel.service.app.repositories.AuditRepository;
import com.hotel.service.app.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class AuditService {
    private final static Logger logger = LoggerFactory.getLogger(AuditService.class);
    @Autowired
    private AuditRepository auditRepository;
    @Async
    public void saveAuditData(String auditDetails) throws IOException {
        final Audit audit=Audit.builder().auditDetails(auditDetails).timestamp(new Date()).build();
        auditRepository.save(audit);
        logger.info("Saved Audit details successfully !");
    }
}
