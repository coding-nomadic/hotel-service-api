package com.hotel.service.app.interceptors;

import com.hotel.service.app.services.AuditService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;




import java.io.IOException;
import java.util.Date;

@Service
public class AuditRequestFiler extends OncePerRequestFilter {
    private final static Logger logger = LoggerFactory.getLogger(AuditRequestFiler.class);

    @Autowired
    private AuditService auditService;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse, @NotNull FilterChain filterChain) throws ServletException, IOException {

        if(httpServletRequest.getMethod().equals("POST") || httpServletRequest.getMethod().equals("GET") || httpServletRequest.getMethod().equals("PUT") || httpServletRequest.getMethod().equals("DELETE") || httpServletRequest.getMethod().equals("HEAD") || httpServletRequest.getMethod().equals("PATCH")){
            try{
                //auditService.saveAuditData(httpServletRequest.getMethod(),httpServletRequest.getServerName(),new Date().toString());
            }catch (Exception exception){
                logger.error("Error Auditing with this message {}",exception.getLocalizedMessage());
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
