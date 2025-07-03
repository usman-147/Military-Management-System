package com.military.asset.backend.repository;

import com.military.asset.backend.entity.Assignment;
import com.military.asset.backend.enums.AssignmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByAssetId(Long assetId);
    List<Assignment> findByAssignedTo(String assignedTo);
    List<Assignment> findByQuantity(int quantity);
    List<Assignment> findByType(AssignmentType type);
    List<Assignment> findByDate(LocalDate date);
}
