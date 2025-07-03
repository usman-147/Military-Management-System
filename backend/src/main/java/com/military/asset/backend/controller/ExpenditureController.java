package com.military.asset.backend.controller;

import com.military.asset.backend.entity.Expenditure;
import com.military.asset.backend.enums.AssignmentType;
import com.military.asset.backend.service.ExpenditureService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenditures")
@CrossOrigin(origins = "*")
public class ExpenditureController {

    private final ExpenditureService expenditureService;

    public ExpenditureController(ExpenditureService expenditureService) {
        this.expenditureService = expenditureService;
    }

    @GetMapping
    public List<Expenditure> getAllExpenditures() {
        return expenditureService.getAllExpenditures();
    }

    @GetMapping("/{id}")
    public Optional<Expenditure> getExpenditureById(@PathVariable Long id) {
        return expenditureService.getExpenditureById(id);
    }

    @GetMapping("/asset/{assetId}")
    public List<Expenditure> getByAssetId(@PathVariable Long assetId) {
        return expenditureService.getByAssetId(assetId);
    }

    @GetMapping("/assignedTo/{assignedTo}")
    public List<Expenditure> getByAssignedTo(@PathVariable String assignedTo) {
        return expenditureService.getByAssignedTo(assignedTo);
    }

    @GetMapping("/quantity/{quantity}")
    public List<Expenditure> getByQuantity(@PathVariable int quantity) {
        return expenditureService.getByQuantity(quantity);
    }

    @GetMapping("/type/{type}")
    public List<Expenditure> getByType(@PathVariable AssignmentType type) {
        return expenditureService.getByType(type);
    }

    @GetMapping("/date/{date}")
    public List<Expenditure> getByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return expenditureService.getByDate(date);
    }

    @PostMapping
    public Expenditure createExpenditure(@RequestBody Expenditure expenditure) {
        return expenditureService.createExpenditure(expenditure);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        expenditureService.deleteById(id);
    }

    @DeleteMapping("/asset/{assetId}")
    public ResponseEntity<String> deleteByAssetId(@PathVariable Long assetId) {
        expenditureService.deleteByAssetId(assetId);
        return ResponseEntity.ok("Deleted expenditures by assetId: " + assetId);
    }

    @DeleteMapping("/assignedTo/{assignedTo}")
    public ResponseEntity<String> deleteByAssignedTo(@PathVariable String assignedTo) {
        expenditureService.deleteByAssignedTo(assignedTo);
        return ResponseEntity.ok("Deleted expenditures for assignedTo: " + assignedTo);
    }

    @DeleteMapping("/quantity/{quantity}")
    public ResponseEntity<String> deleteByQuantity(@PathVariable int quantity) {
        expenditureService.deleteByQuantity(quantity);
        return ResponseEntity.ok("Deleted expenditures with quantity: " + quantity);
    }

    @DeleteMapping("/type/{type}")
    public ResponseEntity<String> deleteByType(@PathVariable AssignmentType type) {
        expenditureService.deleteByType(type);
        return ResponseEntity.ok("Deleted expenditures with type: " + type);
    }

    @DeleteMapping("/date/{date}")
    public ResponseEntity<String> deleteByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        expenditureService.deleteByDate(date);
        return ResponseEntity.ok("Deleted expenditures on date: " + date);
    }
}
