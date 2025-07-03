package com.military.asset.backend.repository;

import com.military.asset.backend.entity.Purchase;
import com.military.asset.backend.entity.Asset;
import com.military.asset.backend.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByAsset(Asset asset);

    List<Purchase> findByBase(Base base);

    List<Purchase> findByQuantity(int quantity);

    List<Purchase> findByDate(LocalDate date);
}
