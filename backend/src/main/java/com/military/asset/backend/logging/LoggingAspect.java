package com.military.asset.backend.logging;

import com.military.asset.backend.entity.TransactionLog;
import com.military.asset.backend.repository.TransactionLogRepository;
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

    private final TransactionLogRepository transactionLogRepository;

    public LoggingAspect(TransactionLogRepository transactionLogRepository) {
        this.transactionLogRepository = transactionLogRepository;
    }

    @AfterReturning("execution(* com.military.asset.backend.controller.*.*(..))")
    public void logAfterOperation(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName().toUpperCase();

        String action = null;
        if (methodName.contains("PURCHASE")) action = "PURCHASE";
        else if (methodName.contains("TRANSFER")) action = "TRANSFER";
        else if (methodName.contains("ASSIGN")) action = "ASSIGN";
        else if (methodName.contains("EXPEND")) action = "EXPEND";

        if (action == null) return;

        Object[] args = joinPoint.getArgs();

        Long assetId = null;
        Long baseId = null;
        int quantity = 0;

        for (Object arg : args) {
            if (arg == null) continue;
            try {
                if (assetId == null && arg.getClass().getMethod("getAssetId") != null) {
                    assetId = (Long) arg.getClass().getMethod("getAssetId").invoke(arg);
                }
                if (baseId == null && arg.getClass().getMethod("getBaseId") != null) {
                    baseId = (Long) arg.getClass().getMethod("getBaseId").invoke(arg);
                }
                if (quantity == 0 && arg.getClass().getMethod("getQuantity") != null) {
                    quantity = (int) arg.getClass().getMethod("getQuantity").invoke(arg);
                }
            } catch (Exception ignored) {}
        }

        String username = "SYSTEM";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            username = auth.getName();
        }

        TransactionLog log = new TransactionLog();
        log.setAction(action);
        log.setAssetId(assetId);
        log.setBaseId(baseId);
        log.setQuantity(quantity);
        log.setUsername(username);
        log.setTimestamp(LocalDateTime.now());

        transactionLogRepository.save(log);
    }
}
