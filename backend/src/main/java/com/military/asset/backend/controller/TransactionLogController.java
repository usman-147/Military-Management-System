package com.military.asset.backend.controller;

import com.military.asset.backend.entity.TransactionLog;
import com.military.asset.backend.service.TransactionLogService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaction-logs")
@CrossOrigin(origins = "*")
public class TransactionLogController {

    private final TransactionLogService transactionLogService;

    public TransactionLogController(TransactionLogService transactionLogService) {
        this.transactionLogService = transactionLogService;
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping
    public List<TransactionLog> getAllLogs() {
        return transactionLogService.getAllLogs();
    }

    @GetMapping("/{id}")
    public Optional<TransactionLog> getLogById(@PathVariable Long id) {
        return transactionLogService.getLogById(id);
    }

    @GetMapping("/user/{user}")
    public List<TransactionLog> getLogsByUser(@PathVariable String user) {
        return transactionLogService.getLogsByUser(user);
    }

    @GetMapping("/operation/{operation}")
    public List<TransactionLog> getLogsByOperation(@PathVariable String operation) {
        return transactionLogService.getLogsByOperation(operation);
    }

    @GetMapping("/timestamp/{timestamp}")
    public List<TransactionLog> getLogsByTimestamp(@PathVariable String timestamp) {
        LocalDateTime parsedTimestamp = LocalDateTime.parse(timestamp);
        return transactionLogService.getLogsBetweenTimestamps(parsedTimestamp, parsedTimestamp);
    }

    @PostMapping
    public TransactionLog createLog(@RequestBody TransactionLog log) {
        return transactionLogService.createLog(log);
    }

    @DeleteMapping("/{id}")
    public void deleteLogById(@PathVariable Long id) {
        transactionLogService.deleteLogById(id);
    }

    @DeleteMapping("/user/{user}")
    public ResponseEntity<String> deleteByUser(@PathVariable String user) {
        transactionLogService.deleteByUser(user);
        return ResponseEntity.ok("Deleted logs for user: " + user);
    }

    @DeleteMapping("/operation/{operation}")
    public ResponseEntity<String> deleteByOperation(@PathVariable String operation) {
        transactionLogService.deleteByOperation(operation);
        return ResponseEntity.ok("Deleted logs with operation: " + operation);
    }

    @DeleteMapping("/timestamp/{timestamp}")
    public ResponseEntity<String> deleteByTimestamp(@PathVariable String timestamp) {
        LocalDateTime parsedTimestamp = LocalDateTime.parse(timestamp);
        transactionLogService.deleteByTimestamp(parsedTimestamp);
        return ResponseEntity.ok("Deleted logs with timestamp: " + timestamp);
    }
}
