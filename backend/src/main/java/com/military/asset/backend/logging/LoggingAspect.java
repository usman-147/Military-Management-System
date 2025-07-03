package com.military.asset.backend.logging;

import com.military.asset.backend.entity.TransactionLog;
import com.military.asset.backend.repository.TransactionLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAspect {

    private final TransactionLogRepository logRepository;

    public LoggingAspect(TransactionLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Pointcut("execution(* com.military.asset.backend.controller.*.*(..))")
    public void controllerMethods() {
    }

    @AfterReturning("controllerMethods()")
    public void logTransaction(JoinPoint joinPoint) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) return;

        HttpServletRequest request = attrs.getRequest();
        String uri = request.getRequestURI();

        // Determine action from URI
        String action = getActionFromUri(uri);

        // Skip logging if not one of the expected actions
        if (action == null) return;

        // Extract sample data (in real use, this should come from args or context)
        TransactionLog log = new TransactionLog();
        log.setAction(action);
        log.setUsername(request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : "anonymous");
        log.setAssetId(0L); // Default or extracted from args
        log.setBaseId(0L);  // Default or extracted from args
        log.setQuantity(0); // Default or extracted from args
        log.setTimestamp(LocalDateTime.now());

        logRepository.save(log);
    }

    private String getActionFromUri(String uri) {
        if (uri.contains("/purchases")) return "PURCHASE";
        if (uri.contains("/transfers")) return "TRANSFER";
        if (uri.contains("/assignments")) return "ASSIGN";
        if (uri.contains("/expenditures")) return "EXPEND";
        return null;
    }
}
