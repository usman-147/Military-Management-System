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

    public List<TransactionLog> getLogsByUser(String user) {
        return transactionLogRepository.findByUser(user);
    }

    public List<TransactionLog> getLogsByOperation(String operation) {
        return transactionLogRepository.findByOperation(operation);
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

    public void deleteByUser(String user) {
        List<TransactionLog> logs = transactionLogRepository.findByUser(user);
        transactionLogRepository.deleteAll(logs);
    }

    public void deleteByOperation(String operation) {
        List<TransactionLog> logs = transactionLogRepository.findByOperation(operation);
        transactionLogRepository.deleteAll(logs);
    }

    public void deleteByTimestamp(LocalDateTime timestamp) {
        List<TransactionLog> logs = transactionLogRepository.findByTimestampBetween(timestamp, timestamp);
        transactionLogRepository.deleteAll(logs);
    }
}
