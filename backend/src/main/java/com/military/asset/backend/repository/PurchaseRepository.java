package com.military.asset.backend.repository;

import com.military.asset.backend.entity.Purchase;
import com.military.asset.backend.entity.Asset;
import com.military.asset.backend.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByAsset(Asset asset);
    List<Purchase> findByBase(Base base);
    List<Purchase> findByQuantity(int quantity);
    List<Purchase> findByDate(LocalDate date);

    @Query("SELECT SUM(p.quantity) FROM Purchase p WHERE p.base.id = :baseId AND p.asset.id = :assetId AND p.date BETWEEN :start AND :end")
    Optional<Integer> sumQuantityByBaseAndAssetAndDate(Long baseId, Long assetId, LocalDate start, LocalDate end);

}
