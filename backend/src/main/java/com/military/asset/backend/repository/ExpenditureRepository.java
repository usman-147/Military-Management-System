package com.military.asset.backend.repository;

import com.military.asset.backend.entity.Expenditure;
import com.military.asset.backend.enums.AssignmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {

    List<Expenditure> findByAssetId(Long assetId);
    List<Expenditure> findByAssignedTo(String assignedTo);
    List<Expenditure> findByQuantity(int quantity);
    List<Expenditure> findByType(AssignmentType type);
    List<Expenditure> findByDate(LocalDate date);

    @Query("SELECT SUM(e.quantity) FROM Expenditure e WHERE e.base.id = :baseId AND e.asset.id = :assetId AND e.date BETWEEN :start AND :end")
    Optional<Integer> sumQuantityByBaseAndAssetAndDate(Long baseId, Long assetId, LocalDate start, LocalDate end);

}
