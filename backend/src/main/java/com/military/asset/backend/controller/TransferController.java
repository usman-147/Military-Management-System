package com.military.asset.backend.controller;

import com.military.asset.backend.entity.Transfer;
import com.military.asset.backend.service.TransferService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transfers")
@CrossOrigin(origins = "*")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping
    public List<Transfer> getAllTransfers() {
        return transferService.getAllTransfers();
    }

    @GetMapping("/{id}")
    public Optional<Transfer> getTransferById(@PathVariable Long id) {
        return transferService.getTransferById(id);
    }

    @GetMapping("/asset/{assetId}")
    public List<Transfer> getTransfersByAssetId(@PathVariable Long assetId) {
        return transferService.getTransfersByAssetId(assetId);
    }

    @GetMapping("/fromBase/{fromBaseId}")
    public List<Transfer> getTransfersByFromBaseId(@PathVariable Long fromBaseId) {
        return transferService.getTransfersByFromBaseId(fromBaseId);
    }

    @GetMapping("/toBase/{toBaseId}")
    public List<Transfer> getTransfersByToBaseId(@PathVariable Long toBaseId) {
        return transferService.getTransfersByToBaseId(toBaseId);
    }

    @GetMapping("/quantity/{quantity}")
    public List<Transfer> getTransfersByQuantity(@PathVariable int quantity) {
        return transferService.getTransfersByQuantity(quantity);
    }

    @GetMapping("/timestamp")
    public List<Transfer> getTransfersByTimestampRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return transferService.getTransfersBetweenTimestamps(start, end);
    }

    @PostMapping
    public Transfer createTransfer(@RequestBody Transfer transfer) {
        return transferService.createTransfer(transfer);
    }

    @DeleteMapping("/{id}")
    public void deleteTransferById(@PathVariable Long id) {
        transferService.deleteTransferById(id);
    }

    @DeleteMapping("/asset/{assetId}")
    public ResponseEntity<String> deleteTransfersByAssetId(@PathVariable Long assetId) {
        transferService.deleteTransfersByAssetId(assetId);
        return ResponseEntity.ok("Deleted transfers with asset ID: " + assetId);
    }

    @DeleteMapping("/fromBase/{fromBaseId}")
    public ResponseEntity<String> deleteTransfersByFromBaseId(@PathVariable Long fromBaseId) {
        transferService.deleteTransfersByFromBaseId(fromBaseId);
        return ResponseEntity.ok("Deleted transfers from base ID: " + fromBaseId);
    }

    @DeleteMapping("/toBase/{toBaseId}")
    public ResponseEntity<String> deleteTransfersByToBaseId(@PathVariable Long toBaseId) {
        transferService.deleteTransfersByToBaseId(toBaseId);
        return ResponseEntity.ok("Deleted transfers to base ID: " + toBaseId);
    }

    @DeleteMapping("/quantity/{quantity}")
    public ResponseEntity<String> deleteTransfersByQuantity(@PathVariable int quantity) {
        transferService.deleteTransfersByQuantity(quantity);
        return ResponseEntity.ok("Deleted transfers with quantity: " + quantity);
    }

    @DeleteMapping("/timestamp")
    public ResponseEntity<String> deleteTransfersByTimestampRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        transferService.deleteTransfersBetweenTimestamps(start, end);
        return ResponseEntity.ok("Deleted transfers between timestamps: " + start + " and " + end);
    }
}
