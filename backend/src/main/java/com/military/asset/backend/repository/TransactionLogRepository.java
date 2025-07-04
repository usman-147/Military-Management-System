package com.military.asset.backend.repository;

import com.military.asset.backend.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {

    List<TransactionLog> findByUsername(String username);
    List<TransactionLog> findByAction(String action);
    List<TransactionLog> findByAssetId(Long assetId);
    List<TransactionLog> findByBaseId(Long baseId);
    List<TransactionLog> findByQuantity(int quantity);
    List<TransactionLog> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
