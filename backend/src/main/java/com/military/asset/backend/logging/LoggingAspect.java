package com.military.asset.backend.logging;

import com.military.asset.backend.entity.TransactionLog;
import com.military.asset.backend.enums.Role;
import com.military.asset.backend.repository.TransactionLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAspect {

    private final TransactionLogRepository logRepository;
    private final HttpServletRequest request;

    public LoggingAspect(TransactionLogRepository logRepository, HttpServletRequest request) {
        this.logRepository = logRepository;
        this.request = request;
    }

    @AfterReturning("execution(* com.military.asset.backend.controller..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            String username = auth.getName();
            String endpoint = request.getRequestURI();
            String method = request.getMethod();

            TransactionLog log = new TransactionLog();
            log.setUser(username);
            log.setOperation(method + " " + endpoint);
            log.setTimestamp(LocalDateTime.now());

            logRepository.save(log);
        }
    }
}
