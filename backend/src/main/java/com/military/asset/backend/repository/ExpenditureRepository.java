package com.military.asset.backend.repository;

import com.military.asset.backend.entity.Expenditure;
import com.military.asset.backend.enums.AssignmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {

    List<Expenditure> findByAssetId(Long assetId);
    List<Expenditure> findByAssignedTo(String assignedTo);
    List<Expenditure> findByQuantity(int quantity);
    List<Expenditure> findByType(AssignmentType type);
    List<Expenditure> findByDate(LocalDate date);
}
