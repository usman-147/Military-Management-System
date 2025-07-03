package com.military.asset.backend.controller;

import com.military.asset.backend.entity.TransactionLog;
import com.military.asset.backend.service.TransactionLogService;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<TransactionLog> getLogById(@PathVariable Long id) {
        Optional<TransactionLog> log = transactionLogService.getLogById(id);
        return log.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-username/{username}")
    public List<TransactionLog> getLogsByUsername(@PathVariable String username) {
        return transactionLogService.getLogsByUsername(username);
    }

    @GetMapping("/by-action/{action}")
    public List<TransactionLog> getLogsByAction(@PathVariable String action) {
        return transactionLogService.getLogsByAction(action);
    }

    @GetMapping("/by-asset/{assetId}")
    public List<TransactionLog> getLogsByAssetId(@PathVariable Long assetId) {
        return transactionLogService.getLogsByAssetId(assetId);
    }

    @GetMapping("/by-base/{baseId}")
    public List<TransactionLog> getLogsByBaseId(@PathVariable Long baseId) {
        return transactionLogService.getLogsByBaseId(baseId);
    }

    @GetMapping("/by-quantity/{quantity}")
    public List<TransactionLog> getLogsByQuantity(@PathVariable int quantity) {
        return transactionLogService.getLogsByQuantity(quantity);
    }

    @GetMapping("/by-timestamp")
    public List<TransactionLog> getLogsBetweenTimestamps(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return transactionLogService.getLogsBetweenTimestamps(start, end);
    }

    @PostMapping
    public TransactionLog createLog(@RequestBody TransactionLog log) {
        return transactionLogService.createLog(log);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogById(@PathVariable Long id) {
        transactionLogService.deleteLogById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-username/{username}")
    public ResponseEntity<Void> deleteByUsername(@PathVariable String username) {
        transactionLogService.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-action/{action}")
    public ResponseEntity<Void> deleteByAction(@PathVariable String action) {
        transactionLogService.deleteByAction(action);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-asset/{assetId}")
    public ResponseEntity<Void> deleteByAssetId(@PathVariable Long assetId) {
        transactionLogService.deleteByAssetId(assetId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-base/{baseId}")
    public ResponseEntity<Void> deleteByBaseId(@PathVariable Long baseId) {
        transactionLogService.deleteByBaseId(baseId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-quantity/{quantity}")
    public ResponseEntity<Void> deleteByQuantity(@PathVariable int quantity) {
        transactionLogService.deleteByQuantity(quantity);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-timestamp")
    public ResponseEntity<Void> deleteByTimestamp(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp) {
        transactionLogService.deleteByTimestamp(timestamp);
        return ResponseEntity.noContent().build();
    }
}
