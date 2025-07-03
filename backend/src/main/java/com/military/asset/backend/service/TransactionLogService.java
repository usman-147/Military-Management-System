package com.military.asset.backend.service;

import com.military.asset.backend.entity.TransactionLog;
import com.military.asset.backend.repository.TransactionLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionLogService {

    private final TransactionLogRepository transactionLogRepository;

    public TransactionLogService(TransactionLogRepository transactionLogRepository) {
        this.transactionLogRepository = transactionLogRepository;
    }

    public List<TransactionLog> getAllLogs() {
        return transactionLogRepository.findAll();
    }

    public Optional<TransactionLog> getLogById(Long id) {
        return transactionLogRepository.findById(id);
    }

    public List<TransactionLog> getLogsByUsername(String username) {
        return transactionLogRepository.findByUsername(username);
    }

    public List<TransactionLog> getLogsByAction(String action) {
        return transactionLogRepository.findByAction(action);
    }

    public List<TransactionLog> getLogsByAssetId(Long assetId) {
        return transactionLogRepository.findByAssetId(assetId);
    }

    public List<TransactionLog> getLogsByBaseId(Long baseId) {
        return transactionLogRepository.findByBaseId(baseId);
    }

    public List<TransactionLog> getLogsByQuantity(int quantity) {
        return transactionLogRepository.findByQuantity(quantity);
    }

    public List<TransactionLog> getLogsBetweenTimestamps(LocalDateTime start, LocalDateTime end) {
        return transactionLogRepository.findByTimestampBetween(start, end);
    }

    public TransactionLog createLog(TransactionLog log) {
        return transactionLogRepository.save(log);
    }

    public void deleteLogById(Long id) {
        transactionLogRepository.deleteById(id);
    }

    public void deleteByUsername(String username) {
        List<TransactionLog> logs = transactionLogRepository.findByUsername(username);
        transactionLogRepository.deleteAll(logs);
    }

    public void deleteByAction(String action) {
        List<TransactionLog> logs = transactionLogRepository.findByAction(action);
        transactionLogRepository.deleteAll(logs);
    }

    public void deleteByAssetId(Long assetId) {
        List<TransactionLog> logs = transactionLogRepository.findByAssetId(assetId);
        transactionLogRepository.deleteAll(logs);
    }

    public void deleteByBaseId(Long baseId) {
        List<TransactionLog> logs = transactionLogRepository.findByBaseId(baseId);
        transactionLogRepository.deleteAll(logs);
    }

    public void deleteByQuantity(int quantity) {
        List<TransactionLog> logs = transactionLogRepository.findByQuantity(quantity);
        transactionLogRepository.deleteAll(logs);
    }

    public void deleteByTimestamp(LocalDateTime timestamp) {
        List<TransactionLog> logs = transactionLogRepository.findByTimestampBetween(timestamp, timestamp);
        transactionLogRepository.deleteAll(logs);
    }
}
