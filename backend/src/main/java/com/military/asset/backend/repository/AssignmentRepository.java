package com.military.asset.backend.repository;

import com.military.asset.backend.entity.Assignment;
import com.military.asset.backend.enums.AssignmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByAssetId(Long assetId);
    List<Assignment> findByAssignedTo(String assignedTo);
    List<Assignment> findByQuantity(int quantity);
    List<Assignment> findByType(AssignmentType type);
    List<Assignment> findByDate(LocalDate date);

    @Query("SELECT SUM(a.quantity) FROM Assignment a WHERE a.base.id = :baseId AND a.asset.id = :assetId AND a.date BETWEEN :start AND :end")
    Optional<Integer> sumQuantityByBaseAndAssetAndDate(Long baseId, Long assetId, LocalDate start, LocalDate end);

}
