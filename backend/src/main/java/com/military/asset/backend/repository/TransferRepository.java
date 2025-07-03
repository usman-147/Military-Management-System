package com.military.asset.backend.repository;

import com.military.asset.backend.entity.Transfer;
import com.military.asset.backend.entity.Asset;
import com.military.asset.backend.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    List<Transfer> findByAsset(Asset asset);

    List<Transfer> findByFromBase(Base fromBase);

    List<Transfer> findByToBase(Base toBase);

    List<Transfer> findByQuantity(int quantity);

    List<Transfer> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
