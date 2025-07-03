package com.military.asset.backend.controller;

import com.military.asset.backend.entity.Purchase;
import com.military.asset.backend.service.PurchaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin(origins = "*")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
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
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public Optional<Purchase> getPurchaseById(@PathVariable Long id) {
        return purchaseService.getPurchaseById(id);
    }

    @GetMapping("/asset/{assetId}")
    public List<Purchase> getPurchasesByAssetId(@PathVariable Long assetId) {
        return purchaseService.getPurchasesByAssetId(assetId);
    }

    @GetMapping("/base/{baseId}")
    public List<Purchase> getPurchasesByBaseId(@PathVariable Long baseId) {
        return purchaseService.getPurchasesByBaseId(baseId);
    }

    @GetMapping("/quantity/{quantity}")
    public List<Purchase> getPurchasesByQuantity(@PathVariable int quantity) {
        return purchaseService.getPurchasesByQuantity(quantity);
    }

    @GetMapping("/date/{date}")
    public List<Purchase> getPurchasesByDate(@PathVariable String date) {
        return purchaseService.getPurchasesByDate(LocalDate.parse(date));
    }

    @PostMapping
    public Purchase createPurchase(@RequestBody Purchase purchase) {
        return purchaseService.createPurchase(purchase);
    }

    @DeleteMapping("/{id}")
    public void deletePurchaseById(@PathVariable Long id) {
        purchaseService.deletePurchaseById(id);
    }

    @DeleteMapping("/asset/{assetId}")
    public ResponseEntity<String> deleteByAssetId(@PathVariable Long assetId) {
        purchaseService.deleteByAssetId(assetId);
        return ResponseEntity.ok("Deleted purchase(s) with assetId: " + assetId);
    }

    @DeleteMapping("/base/{baseId}")
    public ResponseEntity<String> deleteByBaseId(@PathVariable Long baseId) {
        purchaseService.deleteByBaseId(baseId);
        return ResponseEntity.ok("Deleted purchase(s) with baseId: " + baseId);
    }

    @DeleteMapping("/quantity/{quantity}")
    public ResponseEntity<String> deleteByQuantity(@PathVariable int quantity) {
        purchaseService.deleteByQuantity(quantity);
        return ResponseEntity.ok("Deleted purchase(s) with quantity: " + quantity);
    }

    @DeleteMapping("/date/{date}")
    public ResponseEntity<String> deleteByDate(@PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        purchaseService.deleteByDate(parsedDate);
        return ResponseEntity.ok("Deleted purchase(s) on date: " + date);
    }
}
