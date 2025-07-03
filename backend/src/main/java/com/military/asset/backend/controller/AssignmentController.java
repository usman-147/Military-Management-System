package com.military.asset.backend.controller;

import com.military.asset.backend.entity.Assignment;
import com.military.asset.backend.enums.AssignmentType;
import com.military.asset.backend.service.AssignmentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "*")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
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
    public List<Assignment> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    @GetMapping("/{id}")
    public Optional<Assignment> getAssignmentById(@PathVariable Long id) {
        return assignmentService.getAssignmentById(id);
    }

    @GetMapping("/asset/{assetId}")
    public List<Assignment> getByAssetId(@PathVariable Long assetId) {
        return assignmentService.getByAssetId(assetId);
    }

    @GetMapping("/assignedTo/{assignedTo}")
    public List<Assignment> getByAssignedTo(@PathVariable String assignedTo) {
        return assignmentService.getByAssignedTo(assignedTo);
    }

    @GetMapping("/quantity/{quantity}")
    public List<Assignment> getByQuantity(@PathVariable int quantity) {
        return assignmentService.getByQuantity(quantity);
    }

    @GetMapping("/type/{type}")
    public List<Assignment> getByType(@PathVariable AssignmentType type) {
        return assignmentService.getByType(type);
    }

    @GetMapping("/date/{date}")
    public List<Assignment> getByDate(@PathVariable String date) {
        return assignmentService.getByDate(LocalDate.parse(date));
    }

    @PostMapping
    public Assignment createAssignment(@RequestBody Assignment assignment) {
        return assignmentService.createAssignment(assignment);
    }

    @DeleteMapping("/{id}")
    public void deleteAssignmentById(@PathVariable Long id) {
        assignmentService.deleteAssignmentById(id);
    }

    @DeleteMapping("/asset/{assetId}")
    public ResponseEntity<String> deleteByAssetId(@PathVariable Long assetId) {
        assignmentService.deleteByAssetId(assetId);
        return ResponseEntity.ok("Deleted assignments with assetId: " + assetId);
    }

    @DeleteMapping("/assignedTo/{assignedTo}")
    public ResponseEntity<String> deleteByAssignedTo(@PathVariable String assignedTo) {
        assignmentService.deleteByAssignedTo(assignedTo);
        return ResponseEntity.ok("Deleted assignments assigned to: " + assignedTo);
    }

    @DeleteMapping("/quantity/{quantity}")
    public ResponseEntity<String> deleteByQuantity(@PathVariable int quantity) {
        assignmentService.deleteByQuantity(quantity);
        return ResponseEntity.ok("Deleted assignments with quantity: " + quantity);
    }

    @DeleteMapping("/type/{type}")
    public ResponseEntity<String> deleteByType(@PathVariable AssignmentType type) {
        assignmentService.deleteByType(type);
        return ResponseEntity.ok("Deleted assignments with type: " + type);
    }

    @DeleteMapping("/date/{date}")
    public ResponseEntity<String> deleteByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        assignmentService.deleteByDate(localDate);
        return ResponseEntity.ok("Deleted assignments with date: " + date);
    }
}
