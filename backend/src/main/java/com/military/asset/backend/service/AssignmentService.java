package com.military.asset.backend.service;

import com.military.asset.backend.entity.Assignment;
import com.military.asset.backend.enums.AssignmentType;
import com.military.asset.backend.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Optional<Assignment> getAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }

    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    public void deleteAssignmentById(Long id) {
        assignmentRepository.deleteById(id);
    }

    public List<Assignment> getByAssetId(Long assetId) {
        return assignmentRepository.findByAssetId(assetId);
    }

    public List<Assignment> getByAssignedTo(String assignedTo) {
        return assignmentRepository.findByAssignedTo(assignedTo);
    }

    public List<Assignment> getByQuantity(int quantity) {
        return assignmentRepository.findByQuantity(quantity);
    }

    public List<Assignment> getByType(AssignmentType type) {
        return assignmentRepository.findByType(type);
    }

    public List<Assignment> getByDate(LocalDate date) {
        return assignmentRepository.findByDate(date);
    }

    public void deleteByAssetId(Long assetId) {
        List<Assignment> list = assignmentRepository.findByAssetId(assetId);
        assignmentRepository.deleteAll(list);
    }

    public void deleteByAssignedTo(String assignedTo) {
        List<Assignment> list = assignmentRepository.findByAssignedTo(assignedTo);
        assignmentRepository.deleteAll(list);
    }

    public void deleteByQuantity(int quantity) {
        List<Assignment> list = assignmentRepository.findByQuantity(quantity);
        assignmentRepository.deleteAll(list);
    }

    public void deleteByType(AssignmentType type) {
        List<Assignment> list = assignmentRepository.findByType(type);
        assignmentRepository.deleteAll(list);
    }

    public void deleteByDate(LocalDate date) {
        List<Assignment> list = assignmentRepository.findByDate(date);
        assignmentRepository.deleteAll(list);
    }
}
