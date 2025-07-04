package com.military.asset.backend.repository;

import com.military.asset.backend.entity.Transfer;
import com.military.asset.backend.entity.Asset;
import com.military.asset.backend.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    List<Transfer> findByAsset(Asset asset);
    List<Transfer> findByFromBase(Base fromBase);
    List<Transfer> findByToBase(Base toBase);
    List<Transfer> findByQuantity(int quantity);
    List<Transfer> findByTimestampBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT SUM(t.quantity) FROM Transfer t WHERE t.toBase.id = :baseId AND t.asset.id = :assetId AND t.date BETWEEN :start AND :end")
    Optional<Integer> sumQuantityByToBaseAndAssetAndDate(Long baseId, Long assetId, LocalDate start, LocalDate end);

    @Query("SELECT SUM(t.quantity) FROM Transfer t WHERE t.fromBase.id = :baseId AND t.asset.id = :assetId AND t.date BETWEEN :start AND :end")
    Optional<Integer> sumQuantityByFromBaseAndAssetAndDate(Long baseId, Long assetId, LocalDate start, LocalDate end);

}
