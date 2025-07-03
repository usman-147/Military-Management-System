package com.military.asset.backend.logging;

import com.military.asset.backend.entity.TransactionLog;
import com.military.asset.backend.repository.TransactionLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAspect {

    private final TransactionLogRepository transactionLogRepository;
    private final HttpServletRequest request;

    public LoggingAspect(TransactionLogRepository transactionLogRepository, HttpServletRequest request) {
        this.transactionLogRepository = transactionLogRepository;
        this.request = request;
    }

    @AfterReturning("execution(* com.military.asset.backend.controller.*.*(..))")
    public void logAfterOperation(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName().toUpperCase(); // e.g. "createPurchase"

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
        String username = request.getParameter("username");

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

        TransactionLog log = new TransactionLog();
        log.setAction(action);
        log.setAssetId(assetId);
        log.setBaseId(baseId);
        log.setQuantity(quantity);
        log.setUsername(username != null ? username : "UNKNOWN");
        log.setTimestamp(LocalDateTime.now());

        transactionLogRepository.save(log);
    }
}
